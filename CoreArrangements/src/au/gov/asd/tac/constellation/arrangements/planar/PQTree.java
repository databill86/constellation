/*
 * Copyright 2010-2019 Australian Signals Directorate
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package au.gov.asd.tac.constellation.arrangements.planar;

import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author twilight_sparkle
 */
class PQTree {

    public PQNode root;
    public PQNode pertinentRoot;
    public int currentNumber;
    public Set<PQNode>[] leaves;
    public PQNode directionIndicatorLocation = null;
    public int numPertinentLeaves;

    public void addLeaves(final PQNode toNode, final List<Integer> childNums) {
        for (int i : childNums) {
            PQNode leaf = new PQNode(NodeType.LeafNode, i, currentNumber);
            toNode.addChild(leaf);
            leaves[i - 1].add(leaf);
        }
    }

    public PQTree(final int numberNodes) {
        currentNumber = 1;
        this.root = new PQNode(NodeType.PNode);
        leaves = new Set[numberNodes];
        for (int i = 0; i < numberNodes; i++) {
            leaves[i] = new HashSet<>();
        }
    }

    private Deque<PQNode> bubble() {
        final Deque<PQNode> nodesToProcess = new LinkedList<>();
        final Deque<PQNode> nodesToBubble = new LinkedList<>();
        final Set<PQNode> bubbled = new HashSet<>();

        for (PQNode leaf : leaves[currentNumber - 1]) {
//            if (leaf.virtualNum == currentNumber) {
            leaf.pertinentLeafCount = 1;
            if (leaf.parent != null) {
                leaf.parent.pertinentChildCount += 1;
                nodesToBubble.addLast(leaf.parent);
            }
            nodesToProcess.addLast(leaf);
//            }
        }
        numPertinentLeaves = nodesToProcess.size();
        boolean toBreak = false;
        boolean offTheTop = false;
        while (!nodesToBubble.isEmpty()) {
            PQNode toBubble = nodesToBubble.removeFirst();
            if (nodesToBubble.isEmpty() && !offTheTop) {
                toBreak = true;
            }
            if (!bubbled.contains(toBubble)) {
                bubbled.add(toBubble);
                if (toBubble.parent != null) {
                    toBubble.parent.pertinentChildCount += 1;
                    nodesToBubble.addLast(toBubble.parent);
                } else {
                    offTheTop = true;
                }
            }
            if (toBreak) {
                break;
            }
        }
        return nodesToProcess;
    }

    public void reduce() {
        currentNumber++;
        Deque<PQNode> nodesToProcess = bubble();
        PQNode node = null;
        while (!nodesToProcess.isEmpty()) {
            node = nodesToProcess.removeFirst();
            if (node.pertinentLeafCount < numPertinentLeaves) {
                // node is not the root of the pertinent subtree
                node.parent.pertinentLeafCount += node.pertinentLeafCount;
                node.parent.pertinentChildCount -= 1;
                if (node.parent.pertinentChildCount == 0) {
                    nodesToProcess.addLast(node.parent);
                }
                boolean matched
                        = templateL1(node)
                        || templateP1(node)
                        || templateP3(node)
                        || templateP5(node)
                        || templateQ1(node)
                        || templateQ2(node);
            } else {
                // node is the root of the pertinent subtree
                boolean matched
                        = templateL1(node)
                        || templateP1(node)
                        || templateP2(node)
                        || templateP4(node)
                        || templateP6(node)
                        || templateQ1(node)
                        || templateQ3(node) /* ||
                         templateQ2(node)*/;
                // As we are at the pertinent root we will not be processing anything above it and hence we need to reset the counts of its ancestors
                PQNode ancestor = node.parent;
                while (ancestor != null) {
                    ancestor.pertinentChildCount = 0;
                    ancestor.pertinentLeafCount = 0;
                    ancestor = ancestor.parent;
                }
            }
            // reset the now processed node's count ready for the next pass.
            node.pertinentLeafCount = 0;
        }
        pertinentRoot = node;
    }

    public void vertexAddition(List<Integer> virtualNodeNums) {
        PQNode nextPNode = new PQNode(NodeType.PNode);
        if (pertinentRoot.label.equals(NodeLabel.Full)) {
            subtreeReplace(pertinentRoot, nextPNode);
        } else {
            addDirectionIndicator(pertinentRoot);
            boolean first = true;
            for (PQNode node : pertinentRoot.getLabelView(NodeLabel.Full)) {
                if (first) {
                    subtreeReplace(node, nextPNode);
                    first = false;
                } else {
                    pertinentRoot.removeChild(node);
                }
            }
            // There were no full nodes because graph is not planar and they were the most efficient to remove.
            // In this case we simply add the next PNode to the end of the pertinent root
            if (first) {
                pertinentRoot.addChild(nextPNode);
            }
        }
        addLeaves(nextPNode, virtualNodeNums);
    }

    public List<Integer> readPertinentFrontier() {
        // If the last processed node matched P4 or P6, then it is no longer the pertinent root,
        // instead its single partial child is the pertinent root and full children of the last matched
        // node have been moved to this child.
        if (!pertinentRoot.label.equals(NodeLabel.Full) && pertinentRoot.labeledChildren.get(NodeLabel.Full).isEmpty()) {
            pertinentRoot = pertinentRoot.labeledChildren.get(NodeLabel.Partial).iterator().next();
        }
        List<Integer> pertinentFrontier = new LinkedList<>();
        readPertinentFrontier(pertinentRoot, pertinentFrontier);
        return pertinentFrontier;
    }

    private void addDirectionIndicator(PQNode toNode) {
        DirectionIndicator indicator = new DirectionIndicator(currentNumber, false);
        toNode.directionIndicator = indicator;
        directionIndicatorLocation = toNode;
    }

    // reads the current direction indicator if any.
    // Returns -1 if there is no direction indicator or the indicator has the same direction as the frontier.
    // Returns the number of the adjacency list to be reversed if the direction indicator is in the opposite direction to the frontier.
    public int readAndRemoveDirectionIndicator() {
        int listToReverse = -1;
        if (directionIndicatorLocation != null) {
            DirectionIndicator indicator = directionIndicatorLocation.directionIndicator;
            directionIndicatorLocation.directionIndicator = null;
            if (indicator.reversed) {
                listToReverse = indicator.number;
            }
        }
        directionIndicatorLocation = null;
        return listToReverse;
    }

    private void readPertinentFrontier(PQNode node, List<Integer> frontier) {
        switch (node.type) {
            case PNode:
                for (PQNode child : node.labeledChildren.get(NodeLabel.Full)) {
                    readPertinentFrontier(child, frontier);
                }
                break;
            case QNode:
                for (PQNode child : node.children) {
                    if (child.label.equals(NodeLabel.Full)) {
                        readPertinentFrontier(child, frontier);
                    }
                }
                break;
            case LeafNode:
                if (node.virtualNum == currentNumber) {
                    frontier.add(node.realNum);
                }
        }
    }

    // Replace the subtree rooted at existing by the subtree rooted at replacement in the current tree.
    private void subtreeReplace(PQNode existing, PQNode replacement) {
        if (existing == root) {
            root = replacement;
            return;
        }
        PQNode existingParent = existing.parent;
        existingParent.replaceChild(existing, replacement);
    }

    // Removes all PQNodes with the given label from node's list of children.
    // If there are no children with this label, nothing happens and null is returned.
    // If there is only one child with this label, it is returned.
    // If there is more than one child with this label, a new P-Node with the same label is created and is made the parent of all the removed nodes. This new P-node is returned.
    private PQNode encapsulateChildrenWithLabel(PQNode node, NodeLabel label) {
        final int numLabeledChildren = node.labeledChildren.get(label).size();
        if (numLabeledChildren == 0) {
            return null;
        } else if (numLabeledChildren == 1) {
            PQNode labeledNode = node.labeledChildren.get(label).iterator().next();
            node.removeChild(labeledNode);
            return labeledNode;
        }
        PQNode newPNode = new PQNode(NodeType.PNode);
        newPNode.relabel(label);
        // Move all children of node with the given label to be children of the new PNode.
        for (PQNode child : node.getLabelView(label)) {
            node.removeChild(child);
            newPNode.addChild(child);
        }
        return newPNode;
    }

    // Matches leaf nodes
    private boolean templateL1(PQNode candidate) {
        // Check whether candidate is a leaf node
        if (candidate.type != NodeType.LeafNode) {
            return false;
        }
        // If the candidate's virtual number matches the current number, relabel it as full
        if (candidate.virtualNum == currentNumber) {
            candidate.relabel(NodeLabel.Full);
        }
        return true;
    }

    // Matches P-nodes where all children are empty, or all children are full
    private boolean templateP1(PQNode candidate) {
        // Check whether candidate is a P node
        if (candidate.type != NodeType.PNode) {
            return false;
        }
        // Check whether all children of candidate are full - if so relabel candidate as full.
        if (candidate.numChildren() == candidate.labeledChildren.get(NodeLabel.Full).size()) {
            candidate.relabel(NodeLabel.Full);
            return true;
        }
        // If not all children of candidate are full, check if they are all empty.
        boolean matches = (candidate.numChildren() == candidate.labeledChildren.get(NodeLabel.Empty).size());
        if (matches) {
        }
        return matches;
    }

    // Matches P-nodes that are the root of the pertinent subtree containing no partial nodes
    private boolean templateP2(PQNode candidate) {
        // Check whether candidate is a P node with no partial children
        if (candidate.type != NodeType.PNode || !candidate.labeledChildren.get(NodeLabel.Partial).isEmpty()) {
            return false;
        }

        // If there is only one full node we need not change anything.
        if (candidate.labeledChildren.get(NodeLabel.Full).size() == 1) {
            return true;
        }

        // Encapsulate full children and add the encapsulation to candidate. Note there will always be two or more full children for this template to match.
        PQNode fullPNode = encapsulateChildrenWithLabel(candidate, NodeLabel.Full);
        candidate.addChild(fullPNode);

        return true;
    }

    // Matches P-nodes that are not the root of the pertinent subtree containing no partial nodes
    private boolean templateP3(PQNode candidate) {
        // Check whether candidate is a P node with no partial children
        if (candidate.type != NodeType.PNode || !candidate.labeledChildren.get(NodeLabel.Partial).isEmpty()) {
            return false;
        }

        // Encapsulate empty children (note there will always be 1 or more to match this template)
        PQNode emptyPNode = encapsulateChildrenWithLabel(candidate, NodeLabel.Empty);

        // Encapsulate full children (note there will always be 1 or more to match this template)
        PQNode fullPNode = encapsulateChildrenWithLabel(candidate, NodeLabel.Full);

        // Make a new QNode which will take both the empty and full encapsulations as children and replace candidate in the tree. NodeLabel it partial.
        PQNode partialQNode = new PQNode(NodeType.QNode);
        partialQNode.relabel(NodeLabel.Partial);
        partialQNode.addChild(emptyPNode);
        partialQNode.addChild(fullPNode);
        subtreeReplace(candidate, partialQNode);

        return true;
    }

    // Matches P-nodes that are the root of the pertinent subtree containing exactly one partial node
    private boolean templateP4(PQNode candidate) {
        // Check whether candidate is a P node with exaclty one partial child
        if (candidate.type != NodeType.PNode || candidate.labeledChildren.get(NodeLabel.Partial).size() != 1) {
            return false;
        }

        // Get the single partial node which is a child of candidate
        PQNode partialChild = candidate.labeledChildren.get(NodeLabel.Partial).iterator().next();

        // Encapsulate full children and add the encapsulation to the partial child of candidate
        PQNode fullChild = encapsulateChildrenWithLabel(candidate, NodeLabel.Full);
        if (fullChild != null) {
            partialChild.addChild(fullChild);
        }

        candidate.relabel(NodeLabel.Partial);

        return true;
    }

    // Matches P-nodes that are not the root of the pertinent subtree containing exactly one partial node
    private boolean templateP5(PQNode candidate) {
        // Check whether candidate is a P node with exaclty one partial child
        if (candidate.type != NodeType.PNode || candidate.labeledChildren.get(NodeLabel.Partial).size() != 1) {
            return false;
        }

        // Get the single partial node which is a child of candidate
        PQNode partialChild = candidate.labeledChildren.get(NodeLabel.Partial).iterator().next();

        // Encapsulate empty children and add the encapsulation as the first child of the partial child of candidate
        PQNode emptyChild = encapsulateChildrenWithLabel(candidate, NodeLabel.Empty);
        if (emptyChild != null) {
            partialChild.addFirstChild(emptyChild);
        }
        // Encapsulate full children and add to the partial child of candidate
        PQNode fullChild = encapsulateChildrenWithLabel(candidate, NodeLabel.Full);
        if (fullChild != null) {
            partialChild.addChild(fullChild);
        }

        // Replace candidate with its partial child
        candidate.removeChild(partialChild);    // Probably not necessary
        subtreeReplace(candidate, partialChild);
        return true;
    }

    // Matches P-nodes that are the root of the pertinent subtree containing exactly two partial node
    private boolean templateP6(PQNode candidate) {
        // Check whether candidate is a P node with exaclty two partial children.
        if (candidate.type != NodeType.PNode || candidate.labeledChildren.get(NodeLabel.Partial).size() != 2) {
            return false;
        }

        // Get the two partial nodes which are children of candidate, noting the order they actually occur in is not important because it is a P-node.
        Iterator<PQNode> iter = candidate.labeledChildren.get(NodeLabel.Partial).iterator();
        PQNode firstPartialChild = iter.next();
        PQNode secondPartialChild = iter.next();

        // Encapsulate full children and add to the first partial child of candidate
        PQNode fullChild = encapsulateChildrenWithLabel(candidate, NodeLabel.Full);
        if (fullChild != null) {
            firstPartialChild.addChild(fullChild);
        }

        // Reverse the children of the second partial child, concatenate them to first partial child, removing the second partial child from candidate's children in the process.
        secondPartialChild.reverseChildren();
        firstPartialChild.concatenateSibling(secondPartialChild);

        candidate.relabel(NodeLabel.Partial);

        return true;
    }

    // Matches Q-nodes where all children are empty, or all children are full
    private boolean templateQ1(PQNode candidate) {
        // Check whether candidate is a Q node
        if (candidate.type != NodeType.QNode) {
            return false;
        }
        // Check whether all children of candidate are full - if so relabel candidate as full.
        if (candidate.numChildren() == candidate.labeledChildren.get(NodeLabel.Full).size()) {
            candidate.relabel(NodeLabel.Full);
            return true;
        }
        // If not all children of candidate are full, check if they are all empty.
        boolean match = (candidate.numChildren() == candidate.labeledChildren.get(NodeLabel.Empty).size());
        if (match) {
        }
        return match;
    }

    // Matches Q-nodes with at most one partial child, deleting extra partial children if necessary
    private boolean templateQ2(PQNode candidate) {
        // Check whether candidate is a Q node
        if (candidate.type != NodeType.QNode) {
            return false;
        }

        // If this node's children are not in a valid order to be planar, it will be planarized by this cleaning process.
        // Regardless of whether node deletion is performed, the remaining partial child is flattened
        // and reversed as necessary. The entire node is also reversed if necessary.
        List<PQNode> permanentlyRemovedNodes = candidate.cleanSinglyPartialQNode();

        for (PQNode destroyed : permanentlyRemovedNodes) {
            removeLeaves(destroyed);
            numPertinentLeaves -= destroyed.pertinentLeafCount;
        }

        candidate.relabel(NodeLabel.Partial);

        return true;
    }

    // Matches Q-nodes that are the root of the pertinent subtree with exactly two partial children, deleting extra partial children if necessary.
    private boolean templateQ3(PQNode candidate) {
        // Check whether candidate is a Q node with two or more partial children
//        int numPartialChildren = candidate.labeledChildren.get(NodeLabel.Partial).size();
        if (candidate.type != NodeType.QNode /*|| numPartialChildren < 2*/) {
            return false;
        }

        // If this node has more than two partial children or its children are otherwise not in a valid order to be planar,
        // it will be planarized by this cleaning process. Regardless of whether node deletion is performed, the two remaining partial children are flattened and reversed as necessary.
        List<PQNode> permanentlyRemovedNodes = candidate.cleanDoublyPartialQNode();

        for (PQNode destroyed : permanentlyRemovedNodes) {
            removeLeaves(destroyed);
            numPertinentLeaves -= destroyed.pertinentLeafCount;
        }

        candidate.relabel(NodeLabel.Partial);

        return true;
    }

    private void removeLeaves(PQNode node) {
        if (node.type.equals(NodeType.LeafNode)) {
            leaves[node.virtualNum - 1].remove(node);
        } else {
            for (PQNode child : node.children) {
                removeLeaves(child);
            }
        }
    }

}
