/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import model.SEI3RD;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author J. Alfredo Brambila Hdez.
 */
public class JFrameMain extends javax.swing.JFrame {

    SEI3RD modelo;
    /** Creates new form JFrameMain */
    public JFrameMain() {
        initComponents();
	modelo = new SEI3RD();
	cargaInforForm();
	actualizaTablaYGrafica();
	
	ajustarTextoJTextF();
    }
    
    public void ajustarTextoJTextF() {
	this.jTextFieldGama1.setCaretPosition(0);
	this.jTextFieldGama2.setCaretPosition(0);
	this.jTextFieldGama3.setCaretPosition(0);
	this.jTextFieldGama4.setCaretPosition(0);
	
	this.jTextFieldSigma1.setCaretPosition(0);
	this.jTextFieldSigma2.setCaretPosition(0);
	this.jTextFieldSigma3.setCaretPosition(0);
	this.jTextFieldSigma4.setCaretPosition(0);
	
	this.jTextFieldDelta1.setCaretPosition(0);
	this.jTextFieldDelta2.setCaretPosition(0);
	this.jTextFieldDelta3.setCaretPosition(0);
	this.jTextFieldDelta4.setCaretPosition(0);
	
	this.jTextFieldBeta1.setCaretPosition(0);
	this.jTextFieldBeta2.setCaretPosition(0);
	this.jTextFieldBeta3.setCaretPosition(0);
	this.jTextFieldBeta4.setCaretPosition(0);
    }
    
    public void cargaInforForm() {
	
	this.jComboBoxEstadoE.removeAllItems();
	this.jComboBoxEstadoE.addItem("Todos los Estados");
	this.jComboBoxEstadoE.addItem("Susceptibles S");
	this.jComboBoxEstadoE.addItem("Expuestos E");
	this.jComboBoxEstadoE.addItem("Asintomaticos I0");
	this.jComboBoxEstadoE.addItem("Moderados I1");
	this.jComboBoxEstadoE.addItem("Severos I2");
	this.jComboBoxEstadoE.addItem("Criticos I3");
	this.jComboBoxEstadoE.addItem("Recuparados R");
	this.jComboBoxEstadoE.addItem("Muertos D");
	
	this.jComboBoxCiudad.removeAllItems();
	this.jComboBoxCiudad.addItem("Bogotá");
	this.jComboBoxCiudad.addItem("Tampico");
	this.jComboBoxCiudad.addItem("Madero");
	this.jComboBoxCiudad.addItem("Altamira");
	
	this.jComboBoxEscenario.removeAllItems();
	this.jComboBoxEscenario.addItem("Esc 1");
	this.jComboBoxEscenario.addItem("Esc 2");
	this.jComboBoxEscenario.addItem("Esc 3");
	
	double[] gama = modelo.getGamma();
	double[] beta = modelo.getBeta();
	double[] sigma = modelo.getSigma();
	double[] delta = modelo.getDelta();
	
	this.jTextFieldBeta1.setText(String.valueOf(beta[0]));
	this.jTextFieldBeta2.setText(String.valueOf(beta[1]));
	this.jTextFieldBeta3.setText(String.valueOf(beta[2]));
	this.jTextFieldBeta4.setText(String.valueOf(beta[3]));
	
	this.jTextFieldDelta1.setText(String.valueOf(delta[0]));
	this.jTextFieldDelta2.setText(String.valueOf(delta[1]));
	this.jTextFieldDelta3.setText(String.valueOf(delta[2]));
	this.jTextFieldDelta4.setText(String.valueOf(delta[3]));
	
	this.jTextFieldGama1.setText(String.valueOf(gama[0]));
	this.jTextFieldGama2.setText(String.valueOf(gama[1]));
	this.jTextFieldGama3.setText(String.valueOf(gama[2]));
	this.jTextFieldGama4.setText(String.valueOf(gama[3]));
	
	this.jTextFieldSigma1.setText(String.valueOf(sigma[0]));
	this.jTextFieldSigma2.setText(String.valueOf(sigma[1]));
	this.jTextFieldSigma3.setText(String.valueOf(sigma[2]));
	this.jTextFieldSigma4.setText(String.valueOf(sigma[3]));
	
	
    }
    
    public void actualizaTablaYGrafica() {
	String[] columnNames = {"t","S","E","I0","I1","I2","I3","R","D"};
	String[][] data = modelo.getValoresSEI3RD();
	this.jTableSeries.setModel(new DefaultTableModel(data,columnNames));
	
	graficarTodo();
    }
    
    public void graficarTodo() {
	XYSeriesCollection dataset;
	dataset = new XYSeriesCollection();
        XYSeries dataS = new XYSeries("S");
        XYSeries dataE = new XYSeries("E");
        XYSeries dataI0 = new XYSeries("I0");
        XYSeries dataI1 = new XYSeries("I1");
        XYSeries dataI2 = new XYSeries("I2");
        XYSeries dataI3 = new XYSeries("I3");
        XYSeries dataR = new XYSeries("R");
        XYSeries dataD = new XYSeries("D");
        //XYSeries dataNR = new XYSeries("NR");
        
        for(int i=0; i<modelo.getT().length; i++) {
	    dataS.add(modelo.getT()[i],modelo.getS()[i]*modelo.getPobTotal());
            dataE.add(modelo.getT()[i],modelo.getE()[i]*modelo.getPobTotal());
            dataI0.add(modelo.getT()[i],modelo.getI0()[i]*modelo.getPobTotal());
            dataI1.add(modelo.getT()[i],modelo.getI1()[i]*modelo.getPobTotal());
            dataI2.add(modelo.getT()[i],modelo.getI2()[i]*modelo.getPobTotal());
            dataI3.add(modelo.getT()[i],modelo.getI3()[i]*modelo.getPobTotal());
            dataR.add(modelo.getT()[i],modelo.getR()[i]*modelo.getPobTotal()); //
            dataD.add(modelo.getT()[i],modelo.getD()[i]*modelo.getPobTotal());
        }
        
        dataset.addSeries(dataS);
        dataset.addSeries(dataE);
        dataset.addSeries(dataI0);
        dataset.addSeries(dataI1);
        dataset.addSeries(dataI2);
        dataset.addSeries(dataI3);
        dataset.addSeries(dataR); //
        dataset.addSeries(dataD);
        //dataset.addSeries(dataNR);
        
        JFreeChart grafica = ChartFactory.createXYLineChart("SEI3RD", "Días", "Población", dataset);
        ChartPanel cp = new ChartPanel(grafica);
        add(cp);

        Dimension d = this.jPanelGrafica.getSize();
        
        cp.setBounds(20,20,d.width-20,d.height-20);
        
        final XYPlot plot = grafica.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesPaint( 1 , Color.GREEN );
        renderer.setSeriesPaint( 2 , Color.YELLOW );
        renderer.setSeriesPaint( 3 , Color.GRAY );
        renderer.setSeriesPaint( 4 , Color.BLUE );
        renderer.setSeriesPaint( 5 , Color.CYAN );
        renderer.setSeriesPaint( 6 , Color.MAGENTA );
        renderer.setSeriesPaint( 7 , Color.BLACK );
        renderer.setSeriesPaint( 8 , Color.ORANGE );
      
        renderer.setSeriesStroke( 0 , new BasicStroke( 2.0f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
        renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
        renderer.setSeriesStroke( 3 , new BasicStroke( 2.0f ) );
        renderer.setSeriesStroke( 4 , new BasicStroke( 2.0f ) );
        renderer.setSeriesStroke( 5 , new BasicStroke( 2.0f ) );
        renderer.setSeriesStroke( 6 , new BasicStroke( 2.0f ) );
        renderer.setSeriesStroke( 7 , new BasicStroke( 2.0f ) );
        renderer.setSeriesStroke( 8 , new BasicStroke( 2.0f ) );
        
        plot.setRenderer( renderer ); 
        
        this.jPanelGrafica.removeAll();
        this.jPanelGrafica.add(cp,BorderLayout.CENTER);
        this.jPanelGrafica.validate();
    }
    
    public void graficar(int indxGraf, String tituloGraf) {
	XYSeriesCollection dataset;
        dataset = new XYSeriesCollection();
        XYSeries data= new XYSeries(tituloGraf);
        
        Color c=Color.RED;
	//--------
        //modelo.sumaElementosEstado(modelo.getS(),rg,i)*modelo.getPobTotalRS_Region(rg)
        switch(indxGraf) {
            case 1:
                for(int i=0; i<modelo.getT().length; i++) {
                    //data.add(modelo.getT()[i],modelo.getS()[rg][ss][i]*modelo.getPobTotalRS(rg,ss));
                    data.add(modelo.getT()[i],modelo.getS()[i]*modelo.getPobTotal());
                }
                c = Color.RED;
                break;
            case 2:
                for(int i=0; i<modelo.getT().length; i++) {
                    data.add(modelo.getT()[i],modelo.getE()[i]*modelo.getPobTotal());
                }
                c = Color.GREEN;
                break;    
            case 3:
                for(int i=0; i<modelo.getT().length; i++) {
                    data.add(modelo.getT()[i],modelo.getI0()[i]*modelo.getPobTotal());
                }
                c = Color.YELLOW;
                break;
            case 4:
                for(int i=0; i<modelo.getT().length; i++) {
                    data.add(modelo.getT()[i],modelo.getI1()[i]*modelo.getPobTotal());
                }
                c = Color.GRAY;
                break;
            case 5:
                for(int i=0; i<modelo.getT().length; i++) {
                    data.add(modelo.getT()[i],modelo.getI2()[i]*modelo.getPobTotal());
                }
                c = Color.BLUE;
                break;
            case 6:
                for(int i=0; i<modelo.getT().length; i++) {
                    data.add(modelo.getT()[i],modelo.getI3()[i]*modelo.getPobTotal());
                }
                c = Color.CYAN;
                break;
            case 7:
                for(int i=0; i<modelo.getT().length; i++) {
                    data.add(modelo.getT()[i],modelo.getR()[i]*modelo.getPobTotal());
                }
                c = Color.MAGENTA;
                break;
            case 8:
                for(int i=0; i<modelo.getT().length; i++) {
                    data.add(modelo.getT()[i],modelo.getD()[i]*modelo.getPobTotal());
                }
                c = Color.BLACK;
                break;
            
        }

        dataset.addSeries(data);
        JFreeChart grafica = ChartFactory.createXYLineChart(tituloGraf, "Días", "Población", dataset);
        ChartPanel cp = new ChartPanel(grafica);
        add(cp);
                
        Dimension d = this.jPanelGrafica.getSize();
        
        cp.setBounds(20,20,d.width-20,d.height-20);
        
        final XYPlot plot = grafica.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint( 0 , c);
        renderer.setSeriesStroke( 0 , new BasicStroke( 2.0f ) );
        plot.setRenderer( renderer ); 
        
        //this.jPanelGrafica.get
        this.jPanelGrafica.removeAll();
        this.jPanelGrafica.add(cp,BorderLayout.CENTER);
        this.jPanelGrafica.validate();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelDelta = new javax.swing.JPanel();
        jTextFieldDelta1 = new javax.swing.JTextField();
        jTextFieldDelta2 = new javax.swing.JTextField();
        jTextFieldDelta3 = new javax.swing.JTextField();
        jTextFieldDelta4 = new javax.swing.JTextField();
        jPanelGamma = new javax.swing.JPanel();
        jTextFieldGama1 = new javax.swing.JTextField();
        jTextFieldGama2 = new javax.swing.JTextField();
        jTextFieldGama3 = new javax.swing.JTextField();
        jTextFieldGama4 = new javax.swing.JTextField();
        jPanelSigma = new javax.swing.JPanel();
        jTextFieldSigma1 = new javax.swing.JTextField();
        jTextFieldSigma2 = new javax.swing.JTextField();
        jTextFieldSigma3 = new javax.swing.JTextField();
        jTextFieldSigma4 = new javax.swing.JTextField();
        jPanelGamma2 = new javax.swing.JPanel();
        jTextFieldBeta1 = new javax.swing.JTextField();
        jTextFieldBeta2 = new javax.swing.JTextField();
        jTextFieldBeta3 = new javax.swing.JTextField();
        jTextFieldBeta4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxEscenario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxEstadoE = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxCiudad = new javax.swing.JComboBox<>();
        jPanelGrafica = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSeries = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modelo SEI3RD - Madero Tamps.");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parametros"));

        jPanelDelta.setBorder(javax.swing.BorderFactory.createTitledBorder("Delta 1-4"));

        jTextFieldDelta1.setPreferredSize(new java.awt.Dimension(100, 25));

        jTextFieldDelta2.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldDelta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDelta2ActionPerformed(evt);
            }
        });

        jTextFieldDelta3.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldDelta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDelta3ActionPerformed(evt);
            }
        });

        jTextFieldDelta4.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldDelta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDelta4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDeltaLayout = new javax.swing.GroupLayout(jPanelDelta);
        jPanelDelta.setLayout(jPanelDeltaLayout);
        jPanelDeltaLayout.setHorizontalGroup(
            jPanelDeltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeltaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldDelta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDelta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDelta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDelta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDeltaLayout.setVerticalGroup(
            jPanelDeltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDeltaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDeltaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDelta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDelta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDelta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDelta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelGamma.setBorder(javax.swing.BorderFactory.createTitledBorder("Gamma 1-4"));

        jTextFieldGama1.setPreferredSize(new java.awt.Dimension(100, 25));

        jTextFieldGama2.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldGama2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGama2ActionPerformed(evt);
            }
        });

        jTextFieldGama3.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldGama3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGama3ActionPerformed(evt);
            }
        });

        jTextFieldGama4.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldGama4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGama4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGammaLayout = new javax.swing.GroupLayout(jPanelGamma);
        jPanelGamma.setLayout(jPanelGammaLayout);
        jPanelGammaLayout.setHorizontalGroup(
            jPanelGammaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGammaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldGama1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldGama2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldGama3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldGama4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelGammaLayout.setVerticalGroup(
            jPanelGammaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGammaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGammaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldGama1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldGama2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldGama3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldGama4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSigma.setBorder(javax.swing.BorderFactory.createTitledBorder("Sigma 1-4"));

        jTextFieldSigma1.setPreferredSize(new java.awt.Dimension(100, 25));

        jTextFieldSigma2.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldSigma2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSigma2ActionPerformed(evt);
            }
        });

        jTextFieldSigma3.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldSigma3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSigma3ActionPerformed(evt);
            }
        });

        jTextFieldSigma4.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldSigma4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSigma4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSigmaLayout = new javax.swing.GroupLayout(jPanelSigma);
        jPanelSigma.setLayout(jPanelSigmaLayout);
        jPanelSigmaLayout.setHorizontalGroup(
            jPanelSigmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSigmaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldSigma1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSigma2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSigma3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldSigma4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSigmaLayout.setVerticalGroup(
            jPanelSigmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSigmaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSigmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSigma1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSigma2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSigma3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSigma4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelGamma2.setBorder(javax.swing.BorderFactory.createTitledBorder("Beta 1-4"));

        jTextFieldBeta1.setPreferredSize(new java.awt.Dimension(100, 25));

        jTextFieldBeta2.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldBeta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBeta2ActionPerformed(evt);
            }
        });

        jTextFieldBeta3.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldBeta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBeta3ActionPerformed(evt);
            }
        });

        jTextFieldBeta4.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextFieldBeta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBeta4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGamma2Layout = new javax.swing.GroupLayout(jPanelGamma2);
        jPanelGamma2.setLayout(jPanelGamma2Layout);
        jPanelGamma2Layout.setHorizontalGroup(
            jPanelGamma2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGamma2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldBeta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBeta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBeta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldBeta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanelGamma2Layout.setVerticalGroup(
            jPanelGamma2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGamma2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGamma2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBeta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBeta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBeta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBeta4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Aplicar parámetros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelGamma2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelDelta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelGamma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSigma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanelGamma2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDelta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelGamma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelSigma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Escenarios"));

        jLabel1.setText("Escenario:");

        jComboBoxEscenario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxEscenario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxEscenarioItemStateChanged(evt);
            }
        });

        jLabel2.setText("Estado:");

        jComboBoxEstadoE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxEstadoE.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxEstadoEItemStateChanged(evt);
            }
        });

        jLabel3.setText("Ciudad:");

        jComboBoxCiudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCiudad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCiudadItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxEstadoE, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxEscenario, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxEscenario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxEstadoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanelGrafica.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelGraficaLayout = new javax.swing.GroupLayout(jPanelGrafica);
        jPanelGrafica.setLayout(jPanelGraficaLayout);
        jPanelGraficaLayout.setHorizontalGroup(
            jPanelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelGraficaLayout.setVerticalGroup(
            jPanelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTableSeries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableSeries);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldDelta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDelta2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDelta2ActionPerformed

    private void jTextFieldDelta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDelta3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDelta3ActionPerformed

    private void jTextFieldDelta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDelta4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDelta4ActionPerformed

    private void jTextFieldGama2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGama2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGama2ActionPerformed

    private void jTextFieldGama3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGama3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGama3ActionPerformed

    private void jTextFieldGama4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGama4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGama4ActionPerformed

    private void jTextFieldSigma2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSigma2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSigma2ActionPerformed

    private void jTextFieldSigma3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSigma3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSigma3ActionPerformed

    private void jTextFieldSigma4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSigma4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSigma4ActionPerformed

    private void jTextFieldBeta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBeta2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBeta2ActionPerformed

    private void jTextFieldBeta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBeta3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBeta3ActionPerformed

    private void jTextFieldBeta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBeta4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBeta4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
	setParametrosUI();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void setParametrosUI() {
	if(!this.jTextFieldBeta1.getText().isEmpty() && !this.jTextFieldBeta2.getText().isEmpty() 
		&& !this.jTextFieldBeta3.getText().isEmpty() && !this.jTextFieldBeta4.getText().isEmpty()
		&& !this.jTextFieldDelta1.getText().isEmpty() && !this.jTextFieldDelta2.getText().isEmpty() 
		&& !this.jTextFieldDelta3.getText().isEmpty() && !this.jTextFieldDelta4.getText().isEmpty()
		&& !this.jTextFieldGama1.getText().isEmpty() && !this.jTextFieldGama2.getText().isEmpty()
		&& !this.jTextFieldGama3.getText().isEmpty() && !this.jTextFieldGama4.getText().isEmpty()
		&& !this.jTextFieldSigma1.getText().isEmpty() && !this.jTextFieldSigma2.getText().isEmpty()
		&& !this.jTextFieldSigma3.getText().isEmpty() && !this.jTextFieldSigma4.getText().isEmpty()) {
	double[] beta = new double[4];
	double[] delta = new double[4];
	double[] gama = new double[4];
	double[] sigma = new double[4];
	
	beta[0] = Double.parseDouble(this.jTextFieldBeta1.getText());
	beta[1] = Double.parseDouble(this.jTextFieldBeta2.getText());
	beta[2] = Double.parseDouble(this.jTextFieldBeta3.getText());
	beta[3] = Double.parseDouble(this.jTextFieldBeta4.getText());
	//
	delta[0] = Double.parseDouble(this.jTextFieldDelta1.getText());
	delta[1] = Double.parseDouble(this.jTextFieldDelta2.getText());
	delta[2] = Double.parseDouble(this.jTextFieldDelta3.getText());
	delta[3] = Double.parseDouble(this.jTextFieldDelta4.getText());
	//
	gama[0] = Double.parseDouble(this.jTextFieldGama1.getText());
	gama[1] = Double.parseDouble(this.jTextFieldGama2.getText());
	gama[2] = Double.parseDouble(this.jTextFieldGama3.getText());
	gama[3] = Double.parseDouble(this.jTextFieldGama4.getText());
	//
	sigma[0] = Double.parseDouble(this.jTextFieldSigma1.getText());
	sigma[1] = Double.parseDouble(this.jTextFieldSigma2.getText());
	sigma[2] = Double.parseDouble(this.jTextFieldSigma3.getText());
	sigma[3] = Double.parseDouble(this.jTextFieldSigma4.getText());
	
	modelo.setParametros(delta, gama, sigma, beta);
	modelo.simulaModelo();
	
	actualizaTablaYGrafica();
	}
    }
    
    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
	
    }//GEN-LAST:event_formWindowStateChanged

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
	actualizaTablaYGrafica();
    }//GEN-LAST:event_formComponentResized

    private void jComboBoxEstadoEItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxEstadoEItemStateChanged
        // TODO add your handling code here:
	//graficar
	if(this.jComboBoxEstadoE.getSelectedIndex() == 0) {
            this.graficarTodo();
        } else {
	    if(jComboBoxEstadoE.getSelectedIndex() >= 0)
		this.graficar(jComboBoxEstadoE.getSelectedIndex(), jComboBoxEstadoE.getSelectedItem().toString());
	}
    }//GEN-LAST:event_jComboBoxEstadoEItemStateChanged

    private void jComboBoxCiudadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCiudadItemStateChanged
        // TODO add your handling code here:
	if(this.jComboBoxCiudad.getSelectedIndex()>=0) {
	    if(this.jComboBoxCiudad.getSelectedIndex() == 0) {
		modelo.condicionesInicialesBogota();
	    } else if(this.jComboBoxCiudad.getSelectedIndex() == 1) {
		modelo.condicionesInicialesTampico();
	    } else if(this.jComboBoxCiudad.getSelectedIndex() == 2) {
		modelo.condicionesInicialesMadero();
	    } else if(this.jComboBoxCiudad.getSelectedIndex() == 3) {
		modelo.condicionesInicialesAltamira();
	    }
	    
	    setParametrosUI();
	}
    }//GEN-LAST:event_jComboBoxCiudadItemStateChanged

    private void jComboBoxEscenarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxEscenarioItemStateChanged
        // TODO add your handling code here:
	if(this.jComboBoxEscenario.getSelectedIndex()>=0) {
	    //System.out.println("----> Esc");
	    if(this.jComboBoxEscenario.getSelectedIndex() == 0) {
		modelo.setEscenario(1);
	    } else if(this.jComboBoxEscenario.getSelectedIndex() == 1) {
		modelo.setEscenario(2);
	    } else if(this.jComboBoxEscenario.getSelectedIndex() == 2) {
		modelo.setEscenario(3);
	    }
	    setParametrosUI();
	}
    }//GEN-LAST:event_jComboBoxEscenarioItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxCiudad;
    private javax.swing.JComboBox<String> jComboBoxEscenario;
    private javax.swing.JComboBox<String> jComboBoxEstadoE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelDelta;
    private javax.swing.JPanel jPanelGamma;
    private javax.swing.JPanel jPanelGamma2;
    private javax.swing.JPanel jPanelGrafica;
    private javax.swing.JPanel jPanelSigma;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSeries;
    private javax.swing.JTextField jTextFieldBeta1;
    private javax.swing.JTextField jTextFieldBeta2;
    private javax.swing.JTextField jTextFieldBeta3;
    private javax.swing.JTextField jTextFieldBeta4;
    private javax.swing.JTextField jTextFieldDelta1;
    private javax.swing.JTextField jTextFieldDelta2;
    private javax.swing.JTextField jTextFieldDelta3;
    private javax.swing.JTextField jTextFieldDelta4;
    private javax.swing.JTextField jTextFieldGama1;
    private javax.swing.JTextField jTextFieldGama2;
    private javax.swing.JTextField jTextFieldGama3;
    private javax.swing.JTextField jTextFieldGama4;
    private javax.swing.JTextField jTextFieldSigma1;
    private javax.swing.JTextField jTextFieldSigma2;
    private javax.swing.JTextField jTextFieldSigma3;
    private javax.swing.JTextField jTextFieldSigma4;
    // End of variables declaration//GEN-END:variables

}

