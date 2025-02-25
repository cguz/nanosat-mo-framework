/* ----------------------------------------------------------------------------
 * Copyright (C) 2021      European Space Agency
 *                         European Space Operations Centre
 *                         Darmstadt
 *                         Germany
 * ----------------------------------------------------------------------------
 * System                : ESA NanoSat MO Framework
 * ----------------------------------------------------------------------------
 * Licensed under European Space Agency Public License (ESA-PL) Weak Copyleft – v2.4
 * You may not use this file except in compliance with the License.
 *
 * Except as expressly set forth in this License, the Software is provided to
 * You on an "as is" basis and without warranties of any kind, including without
 * limitation merchantability, fitness for a particular purpose, absence of
 * defects or errors, accuracy or non-infringement of intellectual property rights.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * ----------------------------------------------------------------------------
 */
package esa.mo.nmf.ctt.services.mc;

import esa.mo.com.impl.provider.ArchivePersistenceObject;
import esa.mo.com.impl.util.HelperArchive;
import esa.mo.mc.impl.consumer.ParameterConsumerServiceImpl;
import esa.mo.mc.impl.consumer.StatisticConsumerServiceImpl;
import esa.mo.helpertools.connections.ConnectionConsumer;
import esa.mo.helpertools.helpers.HelperTime;
import esa.mo.tools.mowindow.MOWindow;
import java.io.InterruptedIOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.ccsds.moims.mo.com.structures.InstanceBooleanPair;
import org.ccsds.moims.mo.com.structures.InstanceBooleanPairList;
import org.ccsds.moims.mo.com.structures.ObjectIdList;
import org.ccsds.moims.mo.com.structures.ObjectKey;
import org.ccsds.moims.mo.mal.MALException;
import org.ccsds.moims.mo.mal.MALInteractionException;
import org.ccsds.moims.mo.mal.structures.Duration;
import org.ccsds.moims.mo.mal.structures.Identifier;
import org.ccsds.moims.mo.mal.structures.IdentifierList;
import org.ccsds.moims.mo.mal.structures.LongList;
import org.ccsds.moims.mo.mal.structures.Subscription;
import org.ccsds.moims.mo.mal.structures.UpdateHeader;
import org.ccsds.moims.mo.mal.structures.UpdateHeaderList;
import org.ccsds.moims.mo.mal.transport.MALMessageHeader;
import org.ccsds.moims.mo.mc.statistic.StatisticHelper;
import org.ccsds.moims.mo.mc.statistic.consumer.StatisticAdapter;
import org.ccsds.moims.mo.mc.statistic.structures.StatisticCreationRequest;
import org.ccsds.moims.mo.mc.statistic.structures.StatisticCreationRequestList;
import org.ccsds.moims.mo.mc.statistic.structures.StatisticLinkDetails;
import org.ccsds.moims.mo.mc.statistic.structures.StatisticLinkDetailsList;
import org.ccsds.moims.mo.mc.statistic.structures.StatisticValue;
import org.ccsds.moims.mo.mc.statistic.structures.StatisticValueList;
import org.ccsds.moims.mo.mc.structures.ObjectInstancePairList;

/**
 *
 * @author Cesar Coelho
 */
public class StatisticConsumerPanel extends javax.swing.JPanel {

    private final Subscription subscription;
    private StatisticConsumerServiceImpl serviceMCStatistic;
    private ParameterConsumerServiceImpl serviceMCParameter;
    private StatisticLinkTablePanel statisticTable;

    /**
     *
     * @param serviceMCStatistic
     * @param serviceMCParameter
     */
    public StatisticConsumerPanel(StatisticConsumerServiceImpl serviceMCStatistic, ParameterConsumerServiceImpl serviceMCParameter) {
        initComponents();

        statisticTable = new StatisticLinkTablePanel(serviceMCStatistic.getCOMServices().getArchiveService());
        jScrollPane2.setViewportView(statisticTable);

        this.serviceMCStatistic = serviceMCStatistic;
        this.serviceMCParameter = serviceMCParameter;
        
        this.listDefinitionAllButtonActionPerformed(null);

    
        // Subscribe to Statistic Values
        subscription = ConnectionConsumer.subscriptionWildcard();
        try {
            serviceMCStatistic.getStatisticStub().monitorStatisticsRegister(subscription, new StatisticConsumerAdapter());
        } catch (MALInteractionException | MALException ex) {
            Logger.getLogger(StatisticConsumerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removeNotify()
    {
        super.removeNotify();
        IdentifierList ids = new IdentifierList();
        ids.add(subscription.getSubscriptionId());
        try {
            serviceMCStatistic.getStatisticStub().monitorStatisticsDeregister(ids);
        } catch (MALInteractionException | MALException ex) {
            Logger.getLogger(StatisticConsumerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the
     * formAddModifyParameter. WARNING: Do NOT modify this code. The content of
     * this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        actionDefinitionsTable = new javax.swing.JTable();
        parameterTab = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        enableDefinitionAllAgg = new javax.swing.JButton();
        enableDefinitionButtonAgg = new javax.swing.JButton();
        listDefinitionButton = new javax.swing.JButton();
        msgBoxOn2 = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        addLinkButton = new javax.swing.JButton();
        updateLinkButton = new javax.swing.JButton();
        removeLinkButton = new javax.swing.JButton();
        listDefinitionAllButton = new javax.swing.JButton();
        removeLinkAllButton = new javax.swing.JButton();

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Statistic Service - Definitions");
        jLabel6.setToolTipText("");

        jScrollPane2.setHorizontalScrollBar(null);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(796, 380));
        jScrollPane2.setRequestFocusEnabled(false);

        actionDefinitionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, Boolean.TRUE, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Obj Inst Id", "name", "description", "rawType", "rawUnit", "generationEnabled", "updateInterval"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        actionDefinitionsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        actionDefinitionsTable.setAutoscrolls(false);
        actionDefinitionsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        actionDefinitionsTable.setMaximumSize(null);
        actionDefinitionsTable.setMinimumSize(null);
        actionDefinitionsTable.setPreferredSize(null);
        actionDefinitionsTable.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                actionDefinitionsTableComponentAdded(evt);
            }
        });
        jScrollPane2.setViewportView(actionDefinitionsTable);

        parameterTab.setLayout(new java.awt.GridLayout(2, 1));

        enableDefinitionAllAgg.setText("enableGeneration(group=false, 0)");
        enableDefinitionAllAgg.addActionListener(this::enableDefinitionAllAggActionPerformed);
        jPanel1.add(enableDefinitionAllAgg);

        enableDefinitionButtonAgg.setText("enableGeneration");
        enableDefinitionButtonAgg.addActionListener(this::enableDefinitionButtonAggActionPerformed);
        jPanel1.add(enableDefinitionButtonAgg);

        listDefinitionButton.setText("listDefinition()");
        listDefinitionButton.addActionListener(this::listDefinitionButtonActionPerformed);
        jPanel1.add(listDefinitionButton);

        msgBoxOn2.setText("Display Published Statistic Values");
        msgBoxOn2.addActionListener(this::msgBoxOn2ActionPerformed);
        jPanel1.add(msgBoxOn2);

        parameterTab.add(jPanel1);

        addLinkButton.setText("addParameterEvaluation");
        addLinkButton.addActionListener(this::addLinkButtonActionPerformed);
        jPanel5.add(addLinkButton);

        updateLinkButton.setText("updateParameterEvaluation");
        updateLinkButton.addActionListener(this::updateLinkButtonActionPerformed);
        jPanel5.add(updateLinkButton);

        removeLinkButton.setText("removeParameterEvaluation");
        removeLinkButton.addActionListener(this::removeLinkButtonActionPerformed);
        jPanel5.add(removeLinkButton);

        listDefinitionAllButton.setText("listDefinition(\"*\")");
        listDefinitionAllButton.addActionListener(this::listDefinitionAllButtonActionPerformed);
        jPanel5.add(listDefinitionAllButton);

        removeLinkAllButton.setText("removeParameterEvaluation(0)");
        removeLinkAllButton.addActionListener(this::removeLinkAllButtonActionPerformed);
        jPanel5.add(removeLinkAllButton);

        parameterTab.add(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(parameterTab, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(parameterTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listDefinitionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listDefinitionButtonActionPerformed
        
    }//GEN-LAST:event_listDefinitionButtonActionPerformed

    private void addLinkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLinkButtonActionPerformed
        // Create and Show the Action Definition to the user
        StatisticLinkDetails statLinkDetails = new StatisticLinkDetails();
        statLinkDetails.setCollectionInterval(new Duration(2));
        statLinkDetails.setReportingEnabled(true);
        statLinkDetails.setReportingInterval(new Duration(4));
        statLinkDetails.setResetEveryCollection(true);
        statLinkDetails.setSamplingInterval(new Duration(1));
        
//        statLink.setLinkDetails(statLinkDetails);
        
        ObjectKey paramId = new ObjectKey();
        paramId.setDomain(serviceMCParameter.getConnectionDetails().getDomain());
        paramId.setInstId(3L);
//        statLink.setParameterId(paramId);
        

        StatisticCreationRequest request = new StatisticCreationRequest();
        request.setLinkDetails(statLinkDetails);
        request.setParameterId(paramId);
        request.setStatFuncInstId(1L);
        
        MOWindow statDefinitionWindow = new MOWindow(request, true);
        
        StatisticCreationRequestList statLinkList = new StatisticCreationRequestList();
        try {
            statLinkList.add((StatisticCreationRequest) statDefinitionWindow.getObject());
        } catch (InterruptedIOException ex) {
            return;
        }

        try {
            ObjectInstancePairList objIds = this.serviceMCStatistic.getStatisticStub().addParameterEvaluation(statLinkList);

            if (objIds.isEmpty()) {
                return;
            }

            Thread.sleep(500);
            // Get the stored Action Definition from the Archive
            ArchivePersistenceObject comObject = HelperArchive.getArchiveCOMObject(
                    this.serviceMCStatistic.getCOMServices().getArchiveService().getArchiveStub(),
                    StatisticHelper.STATISTICLINK_OBJECT_TYPE,
                    serviceMCStatistic.getConnectionDetails().getDomain(),
                    objIds.get(0).getObjDefInstanceId());

            // Add the Statistic Link to the table
            statisticTable.addEntry(new Identifier("MyStat!"), comObject);
        } catch (MALInteractionException | MALException ex) {
            JOptionPane.showMessageDialog(null, "There was an error with the submitted statistic link.", "Error", JOptionPane.PLAIN_MESSAGE);
            Logger.getLogger(StatisticConsumerPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(StatisticConsumerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_addLinkButtonActionPerformed

    private void updateLinkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateLinkButtonActionPerformed
        if (statisticTable.getSelectedRow() == -1) { // The row is not selected?
            return;  // Well, then nothing to be done here folks!
        }

        ArchivePersistenceObject obj = statisticTable.getSelectedCOMObject();
        MOWindow moObject = new MOWindow(obj.getObject(), true);

        LongList objIds = new LongList();
        objIds.add(statisticTable.getSelectedDefinitionObjId());

        StatisticLinkDetailsList links = new StatisticLinkDetailsList();
        try {
            links.add( ((StatisticLinkDetails) moObject.getObject()) );
        } catch (InterruptedIOException ex) {
            return;
        }

        try {
            this.serviceMCStatistic.getStatisticStub().updateParameterEvaluation(objIds, links);
            this.listDefinitionAllButtonActionPerformed(null);
        } catch (MALInteractionException | MALException ex) {
            Logger.getLogger(StatisticConsumerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_updateLinkButtonActionPerformed

    private void removeLinkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLinkButtonActionPerformed

        if (statisticTable.getSelectedRow() == -1) { // The row is not selected?
            return;  // Well, then nothing to be done here folks!
        }

        LongList longlist = new LongList();
        longlist.add(statisticTable.getSelectedDefinitionObjId());

        try {
            this.serviceMCStatistic.getStatisticStub().removeParameterEvaluation(longlist);
            statisticTable.removeSelectedEntry();
        } catch (MALInteractionException | MALException ex) {
            Logger.getLogger(StatisticConsumerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_removeLinkButtonActionPerformed

    private void listDefinitionAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listDefinitionAllButtonActionPerformed



        
        
    }//GEN-LAST:event_listDefinitionAllButtonActionPerformed

    private void removeLinkAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLinkAllButtonActionPerformed

        Long objId = (long) 0;
        LongList longlist = new LongList();
        longlist.add(objId);

        try {
            this.serviceMCStatistic.getStatisticStub().removeParameterEvaluation(longlist);
            statisticTable.removeAllEntries();
        } catch (MALInteractionException | MALException ex) {
            Logger.getLogger(StatisticConsumerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_removeLinkAllButtonActionPerformed

    private void actionDefinitionsTableComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_actionDefinitionsTableComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_actionDefinitionsTableComponentAdded

    private void enableDefinitionAllAggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableDefinitionAllAggActionPerformed

        Boolean curState;

        if (statisticTable.getSelectedRow() == -1) {  // Used to avoid problems if no row is selected
            StatisticLinkDetails statisticLink = (StatisticLinkDetails) statisticTable.getFirstCOMObject().getObject();
            if (statisticLink != null) {
                curState = statisticLink.getReportingEnabled();
            } else {
                curState = true;
            }
        } else {
            curState = ((StatisticLinkDetails) statisticTable.getSelectedCOMObject().getObject()).getReportingEnabled();
        }

        InstanceBooleanPairList BoolPairList = new InstanceBooleanPairList();
        BoolPairList.add(new InstanceBooleanPair((long) 0, !curState));  // Zero is the wildcard

        try {
            this.serviceMCStatistic.getStatisticStub().enableReporting(false, BoolPairList);
            statisticTable.switchEnabledstatusAll(!curState);
        } catch (MALInteractionException | MALException ex) {
            Logger.getLogger(StatisticConsumerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_enableDefinitionAllAggActionPerformed

    private void enableDefinitionButtonAggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableDefinitionButtonAggActionPerformed
        if (statisticTable.getSelectedRow() == -1) { // The row is not selected?
            return;  // Well, then nothing to be done here folks!
        }

        Boolean curState = ((StatisticLinkDetails) statisticTable.getSelectedCOMObject().getObject()).getReportingEnabled();
        InstanceBooleanPairList BoolPairList = new InstanceBooleanPairList();
        BoolPairList.add(new InstanceBooleanPair((long) 0, !curState));  // Zero is the wildcard

        try {
            this.serviceMCStatistic.getStatisticStub().enableReporting(false, BoolPairList);
            statisticTable.switchEnabledstatus(!curState);
        } catch (MALInteractionException | MALException ex) {
            Logger.getLogger(StatisticConsumerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_enableDefinitionButtonAggActionPerformed

    private void msgBoxOn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgBoxOn2ActionPerformed

    }//GEN-LAST:event_msgBoxOn2ActionPerformed

    public class StatisticConsumerAdapter extends StatisticAdapter {
        @Override
    public void monitorStatisticsNotifyReceived(MALMessageHeader msgHeader, 
            Identifier _Identifier0, UpdateHeaderList lUpdateHeaderList, 
            LongList _LongList2, ObjectIdList _ObjectIdList3, 
            StatisticValueList _StatisticValueList3, Map qosProperties) {

            final long iDiff = System.currentTimeMillis() - msgHeader.getTimestamp().getValue();

            final UpdateHeader updateHeader = lUpdateHeaderList.get(0);
            final String statFunctionName = updateHeader.getKey().getFirstSubKey().getValue();
            final int statLinkObjId = updateHeader.getKey().getSecondSubKey().intValue();
            final int paramObjId = updateHeader.getKey().getThirdSubKey().intValue();

            try {
                if (msgBoxOn2.isSelected() && lUpdateHeaderList.size() != 0 && _StatisticValueList3.size() != 0) {
                    String str = "";
                    str += "Statistic Function name: " + statFunctionName + " | " + "Statistic Link id: " + statLinkObjId + " | " + "Parameter obj Id: " + paramObjId + " | " + "\n";
                    final StatisticValue statisticValue = _StatisticValueList3.get(0);
                    str += "startTime: " + HelperTime.time2readableString(statisticValue.getStartTime()) + "\n";
                    str += "endTime: " + HelperTime.time2readableString(statisticValue.getEndTime()) + "\n";
                    str += "valueTime: " + HelperTime.time2readableString(statisticValue.getValueTime()) + "\n";
                    str += "value: " + statisticValue.getValue().toString() + "\n";
                    str += "sampleCount: " + statisticValue.getSampleCount().toString() + "\n";

                    JOptionPane.showMessageDialog(null, str, "Returned Statistic Value from the Provider", JOptionPane.PLAIN_MESSAGE);
                }

            } catch (NumberFormatException ex) {
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable actionDefinitionsTable;
    private javax.swing.JButton addLinkButton;
    private javax.swing.JButton enableDefinitionAllAgg;
    private javax.swing.JButton enableDefinitionButtonAgg;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton listDefinitionAllButton;
    private javax.swing.JButton listDefinitionButton;
    private javax.swing.JCheckBox msgBoxOn2;
    private javax.swing.JPanel parameterTab;
    private javax.swing.JButton removeLinkAllButton;
    private javax.swing.JButton removeLinkButton;
    private javax.swing.JButton updateLinkButton;
    // End of variables declaration//GEN-END:variables
}
