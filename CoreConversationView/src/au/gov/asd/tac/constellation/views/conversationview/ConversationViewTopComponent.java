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
package au.gov.asd.tac.constellation.views.conversationview;

import au.gov.asd.tac.constellation.visual.fonts.FontUtilities;
import au.gov.asd.tac.constellation.visual.javafx.JavafxStyleManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays the Conversation View. The conversation view
 * displays content on nodes and transactions in the graph in a chat style
 * window.
 *
 * @see Conversation
 * @see ConversationBox
 */
@ConvertAsProperties(
        dtd = "-//au.gov.asd.tac.constellation.views.conversationview//ConversationView//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "ConversationViewTopComponent",
        iconBase = "au/gov/asd/tac/constellation/views/conversationview/resources/conversation_view.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(
        mode = "explorer",
        openAtStartup = false
)
@ActionID(
        category = "Window",
        id = "au.gov.asd.tac.constellation.views.conversationview.ConversationViewTopComponent"
)
@ActionReferences({
    @ActionReference(path = "Menu/Views", position = 300),
    @ActionReference(path = "Shortcuts", name = "CS-C")
})
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_ConversationViewAction",
        preferredID = "ConversationViewTopComponent"
)
@Messages({
    "CTL_ConversationViewAction=Conversation View",
    "CTL_ConversationViewTopComponent=Conversation View",
    "HINT_ConversationViewTopComponent=Conversation View"
})
public final class ConversationViewTopComponent extends TopComponent {

    private JFXPanel container = new JFXPanel();
    private Conversation conversation = new Conversation();
    private ConversationBox conversationBox;

    public ConversationViewTopComponent() {
        initComponents();
        setName(Bundle.CTL_ConversationViewTopComponent());
        setToolTipText(Bundle.HINT_ConversationViewTopComponent());

        setPreferredSize(new Dimension(500, 500));
        setLayout(new BorderLayout());
        add(container, BorderLayout.CENTER);
        Platform.setImplicitExit(false);

        Platform.runLater(() -> {
            conversationBox = new ConversationBox(conversation);
            final Scene scene = new Scene(conversationBox, Color.web("#444444"));
            container.setScene(scene);

            // Style sheet to hide the scroll bars in the text areas in the bubbles
            scene.getStylesheets().add(JavafxStyleManager.getMainStyleSheet());
            scene.rootProperty().get().setStyle(String.format("-fx-font-size:%d;", FontUtilities.getOutputFontSize()));
            scene.getStylesheets().add("/au/gov/asd/tac/constellation/views/conversationview/resources/conversation.css");

            conversation.getGraphUpdateManager().setManaged(true);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        conversation.getGraphUpdateManager().setManaged(true);
    }

    @Override
    public void componentClosed() {
        conversation.getGraphUpdateManager().setManaged(false);
    }

    void writeProperties(java.util.Properties p) {
    }

    void readProperties(java.util.Properties p) {
    }
}
