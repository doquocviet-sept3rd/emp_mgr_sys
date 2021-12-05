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
import java.util.Objects;

/**
 * @author vcoder
 */
public class DepartmentFrame extends JFrame {
    private final IEmployeeService employeeService = new EmployeeService();
    private final IDepartmentService departmentService = new DepartmentService();
    private final IRegionService regionService = new RegionService();
    private final ICountryService countryService = new CountryService();
    private final IProvinceService provinceService = new ProvinceService();
    private final IDistrictService districtService = new DistrictService();
    private final IWardService wardService = new WardService();
    private int isAdd = 0;

    public DepartmentFrame() {
        initComponents();
        createUIComponents();
        load();
    }

    private void createUIComponents() {
        txfAddress.setLineWrap(true);
    }

    private void load() {
        initData(null);
        displayButton(true);
        displayInput(false);
        tblDepts.setRowSelectionInterval(0, 0);
        bindingFromTableToDetail(0);
    }

    private void initData(List<Department> src) {
        try {
            Object[] header = {
                    "id",
                    "name",
                    "manager",
                    "address",
            };
            Object[][] data = new Object[500][header.length];
            List<Department> departments;
            if (src == null) {
                departments = departmentService.findAll();
            } else {
                departments = src;
            }
            for (int i = 0; i < departments.size(); i++) {
                Department department = departments.get(i);
                data[i][0] = department.getId();
                data[i][1] = department.getName();
                data[i][2] = "[" + department.getManagerId() + "] " + department.getManager().getName();
                Ward ward = department.getWard();
                data[i][3] = "[" + ward.getId() + "] " + "Ward: " + ward.getName() +
                        "\r\n[" + ward.getDistrict().getId() + "]" + " District: " + ward.getDistrict().getName() +
                        "\r\n[" + ward.getDistrict().getProvince().getId() + "]" + " Province: " + ward.getDistrict().getProvince().getName() +
                        "\r\n[" + ward.getDistrict().getProvince().getCountry().getId() + "]" + " Country: " + ward.getDistrict().getProvince().getCountry().getName() +
                        "\r\n[" + ward.getDistrict().getProvince().getCountry().getRegion().getId() + "]" + " Region: " + ward.getDistrict().getProvince().getCountry().getRegion().getName();
            }
            tblDepts.setModel(new DefaultTableModel(data, header));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tblDeptsMouseClicked(MouseEvent e) {
        if (isAdd == 0) {
            int row = tblDepts.rowAtPoint(e.getPoint());
            bindingFromTableToDetail(row);
        }
    }

    private void bindingFromTableToDetail(int row) {
        try {
            txfId.setText(tblDepts.getModel().getValueAt(row, 0).toString());
            txfName.setText(tblDepts.getModel().getValueAt(row, 1).toString());
            txfManager.setText(tblDepts.getModel().getValueAt(row, 2).toString());
            txfAddress.setText(tblDepts.getModel().getValueAt(row, 3).toString());
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
            if (JOptionPane.showConfirmDialog(this, "Do you want to delete this department?", "Warning!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
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
        txfName.setEditable(isDisplay);
        tblDepts.setEnabled(!isDisplay);
        txfManager.setCursor(Cursor.getPredefinedCursor(isDisplay ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        txfAddress.setCursor(Cursor.getPredefinedCursor(isDisplay ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
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
        txfName.setText("");
        txfManager.setText("");
        txfAddress.setText("");
    }

    private void btnSaveMouseClicked(MouseEvent e) {
        try {
            String name = txfName.getText();
            long managerId = getIdFromString(txfManager.getText());
            long wardId = getIdFromString(txfAddress.getText());
            if (isAdd == 1) {
                if (departmentService.insert(new Department(name, managerId, wardId)) != null) {
                    JOptionPane.showMessageDialog(this, "Success!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    load();
                    isAdd = 0;
                }
            } else {
                long id = Long.parseLong(txfId.getText());
                if (departmentService.update(new Department(id, name, managerId, wardId))) {
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

    private void txfManagerMouseClicked(MouseEvent e) {
        if (isAdd != 0) {
            try {
                List<Employee> employees = employeeService.findAll();
                Object[] possibilities = new Object[employees.size()];
                int index = 0;
                long managerIdCurrent = getIdFromString(Objects.equals(txfManager.getText(), "") ? "[-1]" : txfManager.getText());
                for (int i = 0; i < employees.size(); i++) {
                    Employee employee = employees.get(i);
                    possibilities[i] = "[" + employee.getId() + "] " + employee.getName();
                    if (employee.getId() == managerIdCurrent) {
                        index = i;
                    }
                }
                String result = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a manager!",
                        "Manager",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[index]
                );
                if (!Objects.equals(result, null)) {
                    txfManager.setText(result);
                }
            } catch (Exception log) {
                log.printStackTrace();
            }
        }
    }

    private void txfAddressMouseClicked(MouseEvent e) {
        if (isAdd != 0) {
            try {
                java.util.List<Region> regions = regionService.findAll();
                Object[] possibilities = new Object[regions.size()];
                for (int i = 0; i < regions.size(); i++) {
                    Region region = regions.get(i);
                    possibilities[i] = "[" + region.getId() + "] " + region.getName();
                }
                String region = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a region!",
                        "Region",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]
                );

                java.util.List<Country> countries = countryService.findAllByRegion(getIdFromString(region));
                possibilities = new Object[countries.size()];
                for (int i = 0; i < countries.size(); i++) {
                    Country country = countries.get(i);
                    possibilities[i] = "[" + country.getId() + "] " + country.getName();
                }
                String country = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a country!",
                        "Country",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]
                );

                java.util.List<Province> provinces = provinceService.findAllByCountry(getIdFromString(country));
                possibilities = new Object[provinces.size()];
                for (int i = 0; i < provinces.size(); i++) {
                    Province province = provinces.get(i);
                    possibilities[i] = "[" + province.getId() + "] " + province.getName();
                }
                String province = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a province!",
                        "Province",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]
                );

                java.util.List<District> districts = districtService.findAllByProvince(getIdFromString(province));
                possibilities = new Object[districts.size()];
                for (int i = 0; i < districts.size(); i++) {
                    District district = districts.get(i);
                    possibilities[i] = "[" + district.getId() + "] " + district.getName();
                }
                String district = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a district!",
                        "District",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]
                );

                List<Ward> wards = wardService.findAllByDistrict(getIdFromString(district));
                possibilities = new Object[wards.size()];
                for (int i = 0; i < wards.size(); i++) {
                    Ward ward = wards.get(i);
                    possibilities[i] = "[" + ward.getId() + "] " + ward.getName();
                }
                String ward = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a ward!",
                        "Ward",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]
                );
                String result = ward +
                        "\r\n" + district +
                        "\r\n" + province +
                        "\r\n" + country +
                        "\r\n" + region;
                txfAddress.setText(result);
            } catch (Exception log) {
                log.printStackTrace();
            }
        }
    }

    private long getIdFromString(String str) {
        return Long.parseLong(str.substring(1, str.indexOf(']')));
    }

    private void btnSearchMouseClicked(MouseEvent e) {
        String filter = String.valueOf(cboxFilter.getSelectedItem());
        initData(departmentService.findAllByKey(txfSearch.getText(), filter));
        displayButton(true);
        displayInput(false);
        tblDepts.setRowSelectionInterval(0, 0);
        bindingFromTableToDetail(0);
    }

    private void btnReloadMouseClicked(MouseEvent e) {
        load();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Do Quoc Viet
        pnlDepts = new JPanel();
        panel5 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblDepts = new JTable();
        pnlDetail = new JPanel();
        label1 = new JLabel();
        txfId = new JTextField();
        label2 = new JLabel();
        txfName = new JTextField();
        label10 = new JLabel();
        txfManager = new JTextField();
        label11 = new JLabel();
        scrollPane2 = new JScrollPane();
        txfAddress = new JTextArea();
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
        setTitle("Department");
        setBackground(new Color(204, 204, 204));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== pnlDepts ========
        {
            pnlDepts.setBackground(new Color(214, 217, 223));
            pnlDepts.setBorder(new TitledBorder("Departments information:"));
            pnlDepts.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
            . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing
            .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
            Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
            ) ,pnlDepts. getBorder () ) ); pnlDepts. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
            public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName (
            ) ) )throw new RuntimeException( ) ;} } );
            pnlDepts.setLayout(null);

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
            pnlDepts.add(panel5);
            panel5.setBounds(new Rectangle(new Point(20, 30), panel5.getPreferredSize()));

            //======== scrollPane1 ========
            {

                //---- tblDepts ----
                tblDepts.setModel(new DefaultTableModel(
                    new Object[][] {
                    },
                    new String[] {
                        null, "name"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, true
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                tblDepts.setBorder(new EtchedBorder());
                tblDepts.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                tblDepts.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblDeptsMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblDepts);
            }
            pnlDepts.add(scrollPane1);
            scrollPane1.setBounds(20, 30, 700, 295);
        }
        contentPane.add(pnlDepts);
        pnlDepts.setBounds(15, 55, 745, 405);

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

            //---- txfId ----
            txfId.setEditable(false);
            pnlDetail.add(txfId);
            txfId.setBounds(100, 25, 220, 28);

            //---- label2 ----
            label2.setText("Name:");
            pnlDetail.add(label2);
            label2.setBounds(25, 65, 58, 16);

            //---- txfName ----
            txfName.setEditable(false);
            pnlDetail.add(txfName);
            txfName.setBounds(100, 60, 220, 28);

            //---- label10 ----
            label10.setText("Manager:");
            pnlDetail.add(label10);
            label10.setBounds(25, 100, 80, 16);

            //---- txfManager ----
            txfManager.setEditable(false);
            txfManager.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    txfManagerMouseClicked(e);
                }
            });
            pnlDetail.add(txfManager);
            txfManager.setBounds(100, 95, 220, 28);

            //---- label11 ----
            label11.setText("Address:");
            pnlDetail.add(label11);
            label11.setBounds(25, 135, 80, 16);

            //======== scrollPane2 ========
            {

                //---- txfAddress ----
                txfAddress.setEditable(false);
                txfAddress.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        txfAddressMouseClicked(e);
                    }
                });
                scrollPane2.setViewportView(txfAddress);
            }
            pnlDetail.add(scrollPane2);
            scrollPane2.setBounds(100, 130, 220, 120);

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
            btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
            btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
            btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
            btnReload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
            btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
            btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSearchMouseClicked(e);
            }
        });
        contentPane.add(btnSearch);
        btnSearch.setBounds(775, 15, 95, 28);

        //---- cboxFilter ----
        cboxFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cboxFilter.setModel(new DefaultComboBoxModel<>(new String[] {
            "no filter",
            "id",
            "name",
            "manager",
            "address"
        }));
        contentPane.add(cboxFilter);
        cboxFilter.setBounds(665, 15, 105, 28);

        //---- btnExit ----
        btnExit.setText("Exit");
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnExitMouseClicked(e);
            }
        });
        contentPane.add(btnExit);
        btnExit.setBounds(1020, 15, 95, 28);

        contentPane.setPreferredSize(new Dimension(1140, 510));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Do Quoc Viet
    private JPanel pnlDepts;
    private JPanel panel5;
    private JScrollPane scrollPane1;
    private JTable tblDepts;
    private JPanel pnlDetail;
    private JLabel label1;
    private JTextField txfId;
    private JLabel label2;
    private JTextField txfName;
    private JLabel label10;
    private JTextField txfManager;
    private JLabel label11;
    private JScrollPane scrollPane2;
    private JTextArea txfAddress;
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
