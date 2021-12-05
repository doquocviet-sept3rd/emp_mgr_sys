package views.Frames.dashboard;

import java.awt.event.*;

import system.entities.*;
import system.services.*;
import system.services.impls.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @author unknown
 */
public class JobFrame extends JFrame {
    private final IDepartmentService departmentService = new DepartmentService();
    private final IJobService jobService = new JobService();
    private int isAdd = 0;

    public JobFrame() {
        initComponents();
        createUIComponents();
        load();
    }

    private void createUIComponents() {
        txfTitle.setLineWrap(true);
    }

    private void load() {
        initData(null);
        displayButton(true);
        displayInput(false);
        tblJobs.setRowSelectionInterval(0, 0);
        bindingFromTableToDetail(0);
    }

    private void initData(List<Job> src) {
        try {
            Object[] header = {
                    "id",
                    "max salary",
                    "min salary",
                    "title",
            };
            Object[][] data = new Object[500][header.length];
            List<Job> jobs;
            if (src == null) {
                jobs = jobService.findAll();
            } else {
                jobs = src;
            }
            for (int i = 0; i < jobs.size(); i++) {
                Job job = jobs.get(i);
                data[i][0] = job.getId();
                data[i][1] = job.getMaxSalary();
                data[i][2] = job.getMinSalary();
                data[i][3] = job.getTitle();
            }
            tblJobs.setModel(new DefaultTableModel(data, header));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tblJobsMouseClicked(MouseEvent e) {
        if (isAdd == 0) {
            int row = tblJobs.rowAtPoint(e.getPoint());
            bindingFromTableToDetail(row);
        }
    }

    private void bindingFromTableToDetail(int row) {
        try {
            txfId.setText(tblJobs.getModel().getValueAt(row, 0).toString());
            txfMax.setText(tblJobs.getModel().getValueAt(row, 1).toString());
            txfMin.setText(tblJobs.getModel().getValueAt(row, 2).toString());
            txfTitle.setText(tblJobs.getModel().getValueAt(row, 3).toString());
        } catch (Exception e) {
            resetInput();
            e.printStackTrace();
        }
    }

    private void btnExitMouseClicked(MouseEvent e) {
        if (JOptionPane.showConfirmDialog(this, "Do you want to exit?", "Warning!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
            System.exit(0);
        }
    }

    private void btnAddMouseClicked(MouseEvent e) {
        displayInput(true);
        displayButton(false);
        resetInput();
        isAdd = 1;
    }

    private void btnUpdateMouseClicked(MouseEvent e) {
        if (!txfId.getText().equals("")) {
            displayInput(true);
            displayButton(false);
            isAdd = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Row is undefine!", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnDeleteMouseClicked(MouseEvent e) {
        if (!txfId.getText().equals("")) {
            if (JOptionPane.showConfirmDialog(this, "Do you want to delete this job?", "Warning!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                if (departmentService.delete(departmentService.findOne(Long.parseLong(txfId.getText())))) {
                    JOptionPane.showMessageDialog(this, "success!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    load();
                } else {
                    JOptionPane.showMessageDialog(this, "Fail!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Row is undefine!", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void displayInput(boolean isDisplay) {
        txfMax.setEditable(isDisplay);
        txfMin.setEditable(isDisplay);
        txfTitle.setEditable(isDisplay);
        tblJobs.setEnabled(!isDisplay);
    }

    private void displayButton(boolean isDisplay) {
        btnAdd.setEnabled(isDisplay);
        btnUpdate.setEnabled(isDisplay);
        btnDelete.setEnabled(isDisplay);
        btnSave.setEnabled(!isDisplay);
        btnCancel.setEnabled(!isDisplay);
        btnReload.setEnabled(isDisplay);
    }

    private void resetInput() {
        txfId.setText("");
        txfMax.setText("");
        txfMin.setText("");
        txfTitle.setText("");
    }

    private void btnSaveMouseClicked(MouseEvent e) {
        try {
            double maxSalary = Double.parseDouble(txfMax.getText());
            double minSalary = Double.parseDouble(txfMin.getText());
            String title = txfTitle.getText();
            if (isAdd == 1) {
                if (jobService.insert(new Job(maxSalary, minSalary, title)) != null) {
                    JOptionPane.showMessageDialog(this, "Success!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    load();
                    isAdd = 0;
                }
            } else {
                long id = Long.parseLong(txfId.getText());
                if (jobService.update(new Job(id, maxSalary, minSalary, title))) {
                    JOptionPane.showMessageDialog(this, "Success!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    load();
                    isAdd = 0;
                }
            }
        } catch (Exception log) {
            JOptionPane.showMessageDialog(this, log);
        }
    }

    private void btnCancelMouseClicked(MouseEvent e) {
        isAdd = 0;
        load();
    }

    private void btnSearchMouseClicked(MouseEvent e) {
        String filter = String.valueOf(cboxFilter.getSelectedItem());
        initData(jobService.findAllByKey(txfSearch.getText(), filter));
        displayButton(true);
        displayInput(false);
        tblJobs.setRowSelectionInterval(0, 0);
        bindingFromTableToDetail(0);
    }

    private void btnReloadMouseClicked(MouseEvent e) {
        load();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Do Quoc Viet
        pnlJobs = new JPanel();
        panel5 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblJobs = new JTable();
        pnlDetail = new JPanel();
        label1 = new JLabel();
        txfId = new JTextField();
        label2 = new JLabel();
        txfMax = new JTextField();
        label10 = new JLabel();
        txfMin = new JTextField();
        label11 = new JLabel();
        scrollPane2 = new JScrollPane();
        txfTitle = new JTextArea();
        pnlControls = new JPanel();
        btnAdd = new JButton();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        btnReload = new JButton();
        btnSave = new JButton();
        btnCancel = new JButton();
        txfSearch = new JTextField();
        btnSearch = new JButton();
        cboxFilter = new JComboBox<>();
        btnExit = new JButton();

        //======== this ========
        setTitle("Job");
        setBackground(new Color(204, 204, 204));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== pnlJobs ========
        {
            pnlJobs.setBackground(new Color(214, 217, 223));
            pnlJobs.setBorder(new TitledBorder("Jobs information:"));
            pnlJobs.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
            javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax
            . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
            . awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,pnlJobs. getBorder () ) ); pnlJobs. addPropertyChangeListener( new java. beans .
            PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .
            equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            pnlJobs.setLayout(null);

            //======== panel5 ========
            {
                panel5.setLayout(null);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel5.getComponentCount(); i++) {
                        Rectangle bounds = panel5.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel5.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel5.setMinimumSize(preferredSize);
                    panel5.setPreferredSize(preferredSize);
                }
            }
            pnlJobs.add(panel5);
            panel5.setBounds(new Rectangle(new Point(20, 30), panel5.getPreferredSize()));

            //======== scrollPane1 ========
            {

                //---- tblJobs ----
                tblJobs.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, "", null},
                        {null, null, null, null},
                    },
                    new String[] {
                        "id", "max salary", "min salary", "title"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, true, true, true
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                tblJobs.setBorder(new EtchedBorder());
                tblJobs.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblJobsMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblJobs);
            }
            pnlJobs.add(scrollPane1);
            scrollPane1.setBounds(20, 30, 700, 295);
        }
        contentPane.add(pnlJobs);
        pnlJobs.setBounds(15, 55, 745, 405);

        //======== pnlDetail ========
        {
            pnlDetail.setBackground(new Color(214, 217, 223));
            pnlDetail.setBorder(new TitledBorder("Detail:"));
            pnlDetail.setLayout(null);

            //---- label1 ----
            label1.setText("Id:");
            label1.setBackground(new Color(204, 204, 204));
            pnlDetail.add(label1);
            label1.setBounds(25, 30, 58, 16);
            pnlDetail.add(txfId);
            txfId.setBounds(100, 25, 220, 28);

            //---- label2 ----
            label2.setText("Max:");
            pnlDetail.add(label2);
            label2.setBounds(25, 65, 58, 16);
            pnlDetail.add(txfMax);
            txfMax.setBounds(100, 60, 220, 28);

            //---- label10 ----
            label10.setText("Min:");
            pnlDetail.add(label10);
            label10.setBounds(25, 100, 80, 16);
            pnlDetail.add(txfMin);
            txfMin.setBounds(100, 95, 220, 28);

            //---- label11 ----
            label11.setText("Title:");
            pnlDetail.add(label11);
            label11.setBounds(25, 135, 80, 16);

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(txfTitle);
            }
            pnlDetail.add(scrollPane2);
            scrollPane2.setBounds(100, 130, 220, 110);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < pnlDetail.getComponentCount(); i++) {
                    Rectangle bounds = pnlDetail.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = pnlDetail.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                pnlDetail.setMinimumSize(preferredSize);
                pnlDetail.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(pnlDetail);
        pnlDetail.setBounds(775, 55, 345, 270);

        //======== pnlControls ========
        {
            pnlControls.setBorder(new TitledBorder("Controls:"));
            pnlControls.setBackground(new Color(214, 217, 223));
            pnlControls.setLayout(null);

            //---- btnAdd ----
            btnAdd.setText("Add");
            btnAdd.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnAddMouseClicked(e);
                }
            });
            pnlControls.add(btnAdd);
            btnAdd.setBounds(20, 35, 95, btnAdd.getPreferredSize().height);

            //---- btnUpdate ----
            btnUpdate.setText("Update");
            btnUpdate.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnUpdateMouseClicked(e);
                }
            });
            pnlControls.add(btnUpdate);
            btnUpdate.setBounds(125, 35, 95, 28);

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnDeleteMouseClicked(e);
                }
            });
            pnlControls.add(btnDelete);
            btnDelete.setBounds(230, 35, 95, 28);

            //---- btnReload ----
            btnReload.setText("Reload");
            btnReload.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnReloadMouseClicked(e);
                }
            });
            pnlControls.add(btnReload);
            btnReload.setBounds(230, 70, 95, 28);

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnSaveMouseClicked(e);
                }
            });
            pnlControls.add(btnSave);
            btnSave.setBounds(20, 70, 95, 28);

            //---- btnCancel ----
            btnCancel.setText("Cancel");
            btnCancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnCancelMouseClicked(e);
                }
            });
            pnlControls.add(btnCancel);
            btnCancel.setBounds(125, 70, 95, 28);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < pnlControls.getComponentCount(); i++) {
                    Rectangle bounds = pnlControls.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = pnlControls.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                pnlControls.setMinimumSize(preferredSize);
                pnlControls.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(pnlControls);
        pnlControls.setBounds(775, 335, 345, 125);
        contentPane.add(txfSearch);
        txfSearch.setBounds(245, 15, 415, txfSearch.getPreferredSize().height);

        //---- btnSearch ----
        btnSearch.setText("Search");
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSearchMouseClicked(e);
            }
        });
        contentPane.add(btnSearch);
        btnSearch.setBounds(775, 15, 95, 28);

        //---- cboxFilter ----
        cboxFilter.setModel(new DefaultComboBoxModel<>(new String[] {
            "no filter",
            "id",
            "max salary",
            "min salary",
            "title"
        }));
        contentPane.add(cboxFilter);
        cboxFilter.setBounds(665, 15, 105, 28);

        //---- btnExit ----
        btnExit.setText("Exit");
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnExitMouseClicked(e);
            }
        });
        contentPane.add(btnExit);
        btnExit.setBounds(1015, 15, 95, 28);

        contentPane.setPreferredSize(new Dimension(1140, 510));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Do Quoc Viet
    private JPanel pnlJobs;
    private JPanel panel5;
    private JScrollPane scrollPane1;
    private JTable tblJobs;
    private JPanel pnlDetail;
    private JLabel label1;
    private JTextField txfId;
    private JLabel label2;
    private JTextField txfMax;
    private JLabel label10;
    private JTextField txfMin;
    private JLabel label11;
    private JScrollPane scrollPane2;
    private JTextArea txfTitle;
    private JPanel pnlControls;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnReload;
    private JButton btnSave;
    private JButton btnCancel;
    private JTextField txfSearch;
    private JButton btnSearch;
    private JComboBox<String> cboxFilter;
    private JButton btnExit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
