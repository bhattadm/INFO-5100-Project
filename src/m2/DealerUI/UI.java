package m2.DealerUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import dataproto.*;
/**
 *
 * @author yiyizhou
 */
public class UI extends javax.swing.JFrame {
                                            // Variables declaration - do not modify//
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel dealerID;
    //expect from login page
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel carID;
    private javax.swing.JTextField carIDText;
    private javax.swing.JLabel category;
    private javax.swing.JComboBox<String> categoryChoice;
    private javax.swing.JLabel color;
    private javax.swing.JComboBox<String> colorChoice;
    private javax.swing.JFormattedTextField dateofmanufacturingText;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel manufactureDate;
    private javax.swing.JLabel mileage;
    private javax.swing.JTextField mileageText;
    private javax.swing.JLabel model;
    private javax.swing.JLabel price;
    private javax.swing.JTextField priceText;
    private javax.swing.JLabel type;
    private javax.swing.JComboBox<String> typeChoice;
    private javax.swing.JComboBox<String> brandChoice;
    private javax.swing.JComboBox<String> modelChoice;
    private DefaultComboBoxModel BMWComboBoxModel, HondaComboBoxModel, AudiComboBoxModel,FordComboBoxModel,TeslaComboBoxModle;

    ArrayList<Vehicle> listOfAllDealers = new ArrayList<Vehicle>();
    ArrayList<Vehicle> listOfDealersCar = new ArrayList<Vehicle>();
    private Object[][] data;
    private String[] columnNames = {"Car ID","Brand","Model","Date of manufacturing","Type","Category","Color","Price","Mileage"};
    private DefaultTableModel tableModel;
    private javax.swing.JTable jTable1;
    private  String DealerIdString;

    /**
     * Creates new form UI
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public UI(Dealer dealer) {
    	try {
			ConnectToDatabase connect = new ConnectToDatabase();
			connect.getConnections();
			DealerIdString=dealer.getId();//Dealer
			initComponents();
			this.setVisible(true);
    	} catch( Exception e) {
    		e.printStackTrace();
    	}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private void initComponents() throws SQLException, ClassNotFoundException {

        dealerID = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        carID = new javax.swing.JLabel();
        carIDText = new javax.swing.JTextField();
        javax.swing.JLabel brand = new javax.swing.JLabel();
        brandChoice = new javax.swing.JComboBox<>();
        modelChoice = new javax.swing.JComboBox<>();

        model = new javax.swing.JLabel();
        manufactureDate = new javax.swing.JLabel();
        dateofmanufacturingText = new javax.swing.JFormattedTextField();
        type = new javax.swing.JLabel();
        typeChoice = new javax.swing.JComboBox<>();
        category = new javax.swing.JLabel();
        categoryChoice = new javax.swing.JComboBox<>();
        color = new javax.swing.JLabel();
        colorChoice = new javax.swing.JComboBox<>();
        price = new javax.swing.JLabel();
        priceText = new javax.swing.JTextField();
        mileage = new javax.swing.JLabel();
        mileageText = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dealerID.setText("Dealer ID: ");
        carID.setText("Car ID: ");
        brand.setText("Brand:");
        brandChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HONDA", "BMW", "AUDI", "FORD", "TESLA" }));

        brandChoice.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    if(brandChoice.getSelectedItem()=="BMW") {
                        modelChoice.setModel(BMWComboBoxModel);
                    }else if(brandChoice.getSelectedItem()=="AUDI") {
                        modelChoice.setModel(AudiComboBoxModel);
                    }else if(brandChoice.getSelectedItem()=="HONDA") {
                        modelChoice.setModel(HondaComboBoxModel);
                    }else if(brandChoice.getSelectedItem()=="TESLA") {
                        modelChoice.setModel(TeslaComboBoxModle);
                    }else if(brandChoice.getSelectedItem()=="FORD") {
                        modelChoice.setModel(FordComboBoxModel);
                    }
                }
            }
        });
        model.setText("Model:");
        setComboBoxModel();
        //default brand at Honda models
        modelChoice.setModel(HondaComboBoxModel);


        manufactureDate.setText("Manufacture date:");

        //plan to get dealer id from another module
       // jLabel1.setText("DEA0001");
        jLabel1.setText(DealerIdString);
//update hints on date format
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        dateofmanufacturingText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(format)));
        dateofmanufacturingText.setText("yyyy-mm-dd");
        java.awt.Color grayColor = new java.awt.Color(128, 128, 128);
        java.awt.Color blackColor = new java.awt.Color(0, 0, 0);
        dateofmanufacturingText.setForeground(grayColor);
        dateofmanufacturingText.addFocusListener(new java.awt.event.FocusListener() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(dateofmanufacturingText.getText().trim().equals("")){
                   dateofmanufacturingText.setText("yyyy-mm-dd");
                   dateofmanufacturingText.setForeground(grayColor);
                }
                else
                    dateofmanufacturingText.setForeground(blackColor);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(dateofmanufacturingText.getText().trim().equals("yyyy-mm-dd")){
                    dateofmanufacturingText.setText("");
                }
                dateofmanufacturingText.setForeground(blackColor);
            }
        });
//end update

        typeChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SEDAN", "CONVERTIBLE", "WAGON", "MINIVAN", "SUV", "VAN", "PICKUP" }));
        type.setText("Type:");
        category.setText("Category:");
        categoryChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NEW", "USED" }));
        color.setText("Color:");
        colorChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BLACK", "WHITE", "GREY", "SILVER", "RED", "YELLOW", "BLUE", "GREEN", "BROWN", "VIOLET" }));
        price.setText("Price ($):");
        priceText.setToolTipText("");
        mileage.setText("Mileage (miles):");
        mileageText.setToolTipText("");

        updateButton.setText("UPDATE");
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    updateButtonMousePressed(evt);
                } catch (SQLException | ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        addButton.setText("ADD");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                try {
                    addButtonMousePressed(evt);
                } catch (ParseException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        deleteButton.setText("DELETE");
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    try {
                        deleteButtonMousePressed(evt);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });

//hide behind interface

        try {

            CarInventoryImplementation carInventory = new CarInventoryImplementation();
            String dealerIDString = jLabel1.getText();
            listOfDealersCar = carInventory.getListOflDealersCar(dealerIDString);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CarInventoryImplementation CarInventory = new CarInventoryImplementation();
        data = CarInventory.convert2Data(listOfDealersCar);
        tableModel = new DefaultTableModel(data, columnNames);
        jTable1 = new JTable(tableModel);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	try {
					jTable1MouseClicked(evt);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jScrollPane1 = new javax.swing.JScrollPane(jTable1);
        jScrollPane1.setViewportView(jTable1);
        resizeColumns();


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(dealerID)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(color)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(colorChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                                .addComponent(price)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(79, 79, 79)
                                                .addComponent(mileage)
                                                .addGap(33, 33, 33)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addComponent(categoryChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(addButton)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(mileageText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(modelChoice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(37, 37, 37))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(manufactureDate)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(dateofmanufacturingText, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)
                                                                .addComponent(type)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(typeChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(21, 21, 21))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(carID)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(carIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(64, 64, 64)
                                                                .addComponent(brand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(updateButton)
                                                                        .addComponent(brandChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(category)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(model, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(169, 169, 169))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(deleteButton)
                                                                .addGap(0, 0, Short.MAX_VALUE))))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dealerID)
                                        .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(updateButton)
                                        .addComponent(deleteButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(brandChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(model, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(modelChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(brand, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(carID)
                                                .addComponent(carIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(manufactureDate)
                                        .addComponent(dateofmanufacturingText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(type)
                                        .addComponent(category)
                                        .addComponent(categoryChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(color)
                                        .addComponent(colorChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(price)
                                        .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mileage)
                                        .addComponent(mileageText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addButton)
                                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //    resize columns
    float[] columnWidthPercentage = {0.15f, 0.1f, 0.1f, 0.15f, 0.1f,0.08f, 0.1f, 0.12f, 0.1f};
    private void resizeColumns() {
        TableColumn column;
        TableColumnModel jTableColumnModel = jTable1.getColumnModel();
        int tW = jTableColumnModel.getTotalColumnWidth();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }
    // to link brand and models
    private void setComboBoxModel() {
        BMWComboBoxModel = new DefaultComboBoxModel();
        BMWComboBoxModel.addElement("X1");
        BMWComboBoxModel.addElement("X2");
        BMWComboBoxModel.addElement("X3");
        BMWComboBoxModel.addElement("X4");

        HondaComboBoxModel = new DefaultComboBoxModel();
        HondaComboBoxModel.addElement("H1");
        HondaComboBoxModel.addElement("H2");
        HondaComboBoxModel.addElement("H3");
        HondaComboBoxModel.addElement("H4");

        AudiComboBoxModel = new DefaultComboBoxModel();
        AudiComboBoxModel.addElement("A3");
        AudiComboBoxModel.addElement("A4");
        AudiComboBoxModel.addElement("Q3");
        AudiComboBoxModel.addElement("Q4");

        FordComboBoxModel = new DefaultComboBoxModel();
        FordComboBoxModel.addElement("F1");
        FordComboBoxModel.addElement("F2");
        FordComboBoxModel.addElement("F3");
        FordComboBoxModel.addElement("F4");

        TeslaComboBoxModle = new DefaultComboBoxModel();
        TeslaComboBoxModle.addElement("X");
        TeslaComboBoxModle.addElement("Y");
        TeslaComboBoxModle.addElement("3");
        TeslaComboBoxModle.addElement("S");
    }

    private void addButtonMousePressed(java.awt.event.MouseEvent evt) throws ParseException, SQLException {//GEN-FIRST:event_addButtonMousePressed
        // TODO add your handling code here:
        Vehicle a = read();
        CarInventoryImplementation carInventory = new  CarInventoryImplementation();
        String message = carInventory.addvehicle(a,tableModel);
        carIDText.setText(" ");
        dateofmanufacturingText.setText("yyyy-mm-dd");
        priceText.setText(" ");
        mileageText.setText(" ");
        JOptionPane.showMessageDialog(this, message);

    }

    private void deleteButtonMousePressed(java.awt.event.MouseEvent evt) throws SQLException {
        Vehicle a = read();
        CarInventoryImplementation carInventory = new  CarInventoryImplementation();
        String message=  carInventory.deleteVehicle(a,tableModel,jTable1);
        carIDText.setText(" ");
        dateofmanufacturingText.setText("yyyy-mm-dd");
        priceText.setText(" ");
        mileageText.setText(" ");
        JOptionPane.showMessageDialog(this, message);
    }
    private void updateButtonMousePressed(java.awt.event.MouseEvent evt) throws SQLException, ParseException {
        Vehicle v = read();
        CarInventoryImplementation carInventory = new  CarInventoryImplementation();
        String message = carInventory.modifyVehicle(v,tableModel,jTable1);
        carIDText.setText(" ");
        dateofmanufacturingText.setText("yyyy-mm-dd");
        priceText.setText(" ");
        mileageText.setText(" ");
        JOptionPane.showMessageDialog(this, message);

    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) throws ParseException{
    	int selectedRowIndex = jTable1.getSelectedRow();
        carIDText.setText(tableModel.getValueAt(selectedRowIndex, 0).toString());
        brandChoice.setSelectedItem(String.valueOf(jTable1.getValueAt(selectedRowIndex, 1).toString()));
        modelChoice.setSelectedItem(String.valueOf(jTable1.getValueAt(selectedRowIndex, 2).toString()));
        dateofmanufacturingText.setText(String.valueOf(jTable1.getValueAt(selectedRowIndex, 3)));
        typeChoice.setSelectedItem(tableModel.getValueAt(selectedRowIndex, 4).toString());
        categoryChoice.setSelectedItem(tableModel.getValueAt(selectedRowIndex, 5).toString());
        colorChoice.setSelectedItem(tableModel.getValueAt(selectedRowIndex, 6).toString());
        priceText.setText(tableModel.getValueAt(selectedRowIndex, 7).toString());
        mileageText.setText(tableModel.getValueAt(selectedRowIndex, 8).toString());

    }


    private Vehicle read()
    {
    	String dealerIDString = jLabel1.getText();
        String carIDString = carIDText.getText();
        String brandString = brandChoice.getSelectedItem().toString();
        String modelString = modelChoice.getSelectedItem().toString();
        String priceString = priceText.getText();
        String dateofmanufacturingString = dateofmanufacturingText.getText();
        String typeString = typeChoice.getSelectedItem().toString();
        String categoryString = categoryChoice.getSelectedItem().toString();
        String colorString = colorChoice.getSelectedItem().toString();
        String mileageString = mileageText.getText();
        float price = Float.valueOf(priceString);
        float mileage = Float.valueOf(mileageString);
        Vehicle a = new Vehicle(carIDString,dealerIDString,brandString,modelString,dateofmanufacturingString,typeString,categoryString,colorString,price,mileage);
        return a;
    }
    /**
     * @param args the command line arguments
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void main(String args[]) throws SQLException, ClassNotFoundException {

    	 Dealer d = new Dealer();
    	 d.setId("DEA0001");
         new UI(d);


    }


}
