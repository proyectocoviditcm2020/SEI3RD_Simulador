/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Alfredo
 */
public class SEI3RD {

    // poblacion Cd. Madero
    private int pobTotal = 327308;
    private String region = "TAMPICO";
    
    private double w = 1.0;
    private int dias = 365;
    private double[] A = new double[2];
    private double[] delta = new double[4];
    private double[] gamma = new double[4];
    private double[] sigma = new double[4];
    private double[] beta = new double[4];
    /*double beta0;
    double beta1;
    double beta2;
    double beta3;*/
    private int escenario = 1;
    
    private double[] S;
    private double[] E;
    private double[] I0;
    private double[] I1;
    private double[] I2;
    private double[] I3;
    private double[] R;
    private double[] D;
    private double[] t;
    
    public SEI3RD() {
	//CSVDataGet data = new CSVDataGet();
	w = 1.0;
	dias = 365;
	A = new double[2];
	delta = new double[4];
	gamma = new double[4];
	sigma = new double[4];
	escenario = 1;
	
	t = llenaDias(dias+1);
        
        S = zeros(dias+1);
        E = zeros(dias+1);
        I0 = zeros(dias+1);
        I1 = zeros(dias+1);
        I2 = zeros(dias+1);
        I3 = zeros(dias+1);
        R = zeros(dias+1);
        D = zeros(dias+1);
	
	t = llenaDias(dias+1);
	
	parametrosDefault();
	condicionesInicialesBogota();
	simulaModelo();
    }
    
    public void parametrosDefault() {
	delta[0] = ((double) 3)/10;
        delta[1] = ((double) 4)/5;
        delta[2] = ((double) 5)/7;
        delta[3] = ((double) 1)/2;
        //delta[3] = 0.99;
	
	gamma[0] = ((double) 1)/10;
        gamma[1] = ((double) 1)/8;
        gamma[2] = ((double) 1)/8;
        gamma[3] = ((double) 1)/10;

        sigma[0] = ((double) 1)/4.1;
        sigma[1] = ((double) 1)/5;
        sigma[2] = ((double) 1)/6;
        sigma[3] = ((double) 1)/10;
    }
    
    public void setParametros(double d[],double[] g, double[] s, double[] b) {
	this.delta = d;
	this.gamma = g;
	this.sigma = s;
	this.beta = b;
    }
    
    public void condicionesInicialesBogota() {
	this.parametrosDefault();
	this.pobTotal = 7413000;
	this.region = "BOGOTÁ";
	S[0] = (double)pobTotal/pobTotal; //* 1
        I1[0] = ((double) 115)/pobTotal;
        I2[0] = ((double) 1)/pobTotal;
        I3[0] = 0.0;
        R[0] = 0.0;
        D[0] = 0.0;

	E[0] = ((double) 200)/pobTotal;
        I0[0] = ((double) 225)/pobTotal;
        
        // correccion de formula
        S[0] = S[0] - (I0[0] + I1[0] + I2[0] + I3[0] + R[0] + D[0] + E[0]);
        
                
        //βi(t) = (1 − Ai(t))βi0 + Ai(t)βi1 i=0,1
        beta[0] = (1 - A[0]) * 0.3271875 + A[0] * -2.98 * Math.log(1-0.01);
        beta[1] = (1 - A[1]) * -10 * Math.log(1 - 0.015) + A[1] * -2.98 * Math.log(1 - 0.015);
        //βi, i = 2, 3  β2 = −2 log(1 − 0,01), β3 = −2 log(1−0,01)
        beta[2] = -2 * Math.log(1 - 0.01);
        beta[3] = -2 * Math.log(1 - 0.01);
    }
    
    public void condicionesInicialesMadero() {
	//216664
	this.pobTotal = 216664;
	this.region = "MADERO";
	S[0] = (double)pobTotal/pobTotal; //* 1
        I1[0] = ((double) 2)/pobTotal;
        I2[0] = 0.0;
        I3[0] = 0.0;
        R[0] = 0.0;
        D[0] = 0.0;

        E[0] = 0.0;
        I0[0] = 0.0;
        
        // correccion de formula
        S[0] = S[0] - (I0[0] + I1[0] + I2[0] + I3[0] + R[0] + D[0] + E[0]);
        
                
        //βi(t) = (1 − Ai(t))βi0 + Ai(t)βi1 i=0,1
        beta[0] = (1 - A[0]) * 0.3271875 + A[0] * -2.98 * Math.log(1-0.01);
        beta[1] = (1 - A[1]) * -10 * Math.log(1 - 0.015) + A[1] * -2.98 * Math.log(1 - 0.015);
        //βi, i = 2, 3  β2 = −2 log(1 − 0,01), β3 = −2 log(1−0,01)
        beta[2] = -2 * Math.log(1 - 0.01);
        beta[3] = -2 * Math.log(1 - 0.01);
        
        
    }
    
    public void condicionesInicialesTampico() {
	//327308
	this.pobTotal = 327308;
	this.region = "TAMPICO";
	S[0] = (double)pobTotal/pobTotal; //* 1
        I1[0] = ((double) 1)/pobTotal;
        I2[0] = 0.0;
        I3[0] = 0.0;
        R[0] = 0.0;
        D[0] = 0.0;

        E[0] = 0.0;
        I0[0] = 0.0;
        
        // correccion de formula
        S[0] = S[0] - (I0[0] + I1[0] + I2[0] + I3[0] + R[0] + D[0] + E[0]);
        
                
        //βi(t) = (1 − Ai(t))βi0 + Ai(t)βi1 i=0,1
        beta[0] = (1 - A[0]) * 0.3271875 + A[0] * -2.98 * Math.log(1-0.01);
        beta[1] = (1 - A[1]) * -10 * Math.log(1 - 0.015) + A[1] * -2.98 * Math.log(1 - 0.015);
        //βi, i = 2, 3  β2 = −2 log(1 − 0,01), β3 = −2 log(1−0,01)
        beta[2] = -2 * Math.log(1 - 0.01);
        beta[3] = -2 * Math.log(1 - 0.01);
        
        
    }
    
    public void condicionesInicialesAltamira() {
	//246549
	this.pobTotal = 246549;
	this.region = "ALTAMIRA";
	S[0] = (double)pobTotal/pobTotal; //* 1
        I1[0] = ((double) 1)/pobTotal;
        I2[0] = 0.0;
        I3[0] = 0.0;
        R[0] = 0.0;
        D[0] = 0.0;

        E[0] = 0.0;
        I0[0] = 0.0;
        
        // correccion de formula
        S[0] = S[0] - (I0[0] + I1[0] + I2[0] + I3[0] + R[0] + D[0] + E[0]);
        
                
        //βi(t) = (1 − Ai(t))βi0 + Ai(t)βi1 i=0,1
        beta[0] = (1 - A[0]) * 0.3271875 + A[0] * -2.98 * Math.log(1-0.01);
        beta[1] = (1 - A[1]) * -10 * Math.log(1 - 0.015) + A[1] * -2.98 * Math.log(1 - 0.015);
        //βi, i = 2, 3  β2 = −2 log(1 − 0,01), β3 = −2 log(1−0,01)
        beta[2] = -2 * Math.log(1 - 0.01);
        beta[3] = -2 * Math.log(1 - 0.01);
        
        
    }
    
    public void simulaModelo() {
	
	//escenario = 1; // 2,3
	for (int n = 0; n < dias; n++) {
	    //System.out.println("n: " + n);
	    switch (escenario) {
		case 2:
		    //System.out.println("Escenario 2");
		    if (n >= 0 && n < 5) {
			A[0] = 0.3; // asintomaticos
			A[1] = 0.3; // sintomaticos
		    } else if (n >= 5 && n < 43) {
			A[0] = 0.6;
			A[1] = 0.6;
		    } else if (n >= 43 && n < dias) {
			A[0] = 0.3;
			A[1] = 0.5;
		    }
		    break;
		case 3:
		    //System.out.println("Escenario 3");
		    if (n >= 0 && n < 5) {
			A[0] = 0.3;
			A[1] = 0.3;
		    } else if (n >= 5 && n < 97) {
			A[0] = 0.7;
			A[1] = 0.7;
		    } else if (n >= 97 && n < dias) {
			A[0] = 0.3;
			A[1] = 0.5;
		    }
		    break;
		default:
		    A[0]=0;
		    A[1]=0;
	    }

	    beta[0] = (1 - A[0]) * 0.3271875 + A[0] * -2.98 * Math.log(1 - 0.01);//revisar
	    beta[1] = (1 - A[1]) * -10 * Math.log(1 - 0.015) + A[1] * -2.98 * Math.log(1 - 0.015);

	    
	    //−S(β0(t)I0 + β1(t)I1 + β2I2 + β3I3)
	    S[n + 1] = S[n] - S[n] * (beta[0] * I0[n] + beta[1] * I1[n] + beta[2] * I2[n] + beta[3] * I3[n]);

	    // S(β0(t)I0 + β1(t)I1 + β2I2 + β3I3) − ωE
	    E[n + 1] = E[n] + S[n] * (beta[0] * I0[n] + beta[1] * I1[n] + beta[2] * I2[n] + beta[3] * I3[n]) - w * E[n];

	    // ωE − δ0γ0I0 − (1 − δ0)σ0I0
	    I0[n + 1] = I0[n] + w * E[n] - delta[0] * gamma[0] * I0[n] - (1 - delta[0]) * sigma[0] * I0[n];

	    //  (1 − δ0)σ0I0 − δ1γ1I1 − (1 − δ1)σ1I1
	    I1[n + 1] = I1[n] + (1 - delta[0]) * sigma[0] * I0[n] - delta[1] * gamma[1] * I1[n] - (1 - delta[1]) * sigma[1] * I1[n];

	    // (1 − δ1)σ1I1 − δ2γ2I2 − (1 − δ2)σ2I2
	    I2[n + 1] = I2[n] + (1 - delta[1]) * sigma[1] * I1[n] - delta[2] * gamma[2] * I2[n] - (1 - delta[2]) * sigma[2] * I2[n];

	    // (1 − δ2)σ2I2 − δ3γ3I3 − (1 − δ3)σ3I3
	    I3[n + 1] = I3[n] + (1 - delta[2]) * sigma[2] * I2[n] - delta[3] * gamma[3] * I3[n] - (1 - delta[3]) * sigma[3] * I3[n];

	    // δ0γ0I0 + δ1γ1I1 + δ2γ2I2 + δ3γ3I3
	    R[n + 1] = R[n] + delta[0] * gamma[0] * I0[n] + delta[1] * gamma[1] * I1[n] + delta[2] * gamma[2] * I2[n] + delta[3] * gamma[3] * I3[n];

	    //  (1 − δ3)σ3I3
	    D[n + 1] = D[n] + (1 - delta[3]) * sigma[3] * I3[n];

	}
	guardarEnArchivo("resultados_E"+escenario+".csv", t, S, E,I0,I1,I2,I3,R,D);
	
    }
        
    public String[][] getValoresSEI3RD() {
        String[][] valores = new String[this.dias][9];
        for(int i=0; i<dias; i++) {
            valores[i][0] = String.valueOf(t[i]);
            //valores[i][1] = String.valueOf(S[rgi][ssi][i]*poblacion[rgi][ssi]);
            valores[i][1] = String.valueOf(S[i]*this.pobTotal);
            valores[i][2] = String.valueOf(E[i]*this.pobTotal);
            valores[i][3] = String.valueOf(I0[i]*this.pobTotal);
            valores[i][4] = String.valueOf(I1[i]*this.pobTotal);
            valores[i][5] = String.valueOf(I2[i]*this.pobTotal);
            valores[i][6] = String.valueOf(I3[i]*this.pobTotal);
            valores[i][7] = String.valueOf(R[i]*this.pobTotal);
            valores[i][8] = String.valueOf(D[i]*this.pobTotal);
        }
        return valores;
    }
    
    public  void guardarEnArchivo(String nombreArchivo, double t[], double S[], double E[],double I0[],double I1[],double I2[],double I3[], double R[], double D[]) {
        FileWriter archivo = null;
        PrintWriter pw = null;
        
        try {
            archivo = new FileWriter(nombreArchivo);
            pw = new PrintWriter(archivo);
            
	    /*System.out.println("Tam: " + this.t.length);
	    System.out.println("Tam: " + this.S.length);
	    System.out.println("Tam: " + this.E.length);
	    System.out.println("Tam: " + this.I0.length);
	    System.out.println("Tam: " + this.I1.length);	    
	    System.out.println("Tam: " + this.I2.length);
	    System.out.println("Tam: " + this.I3.length);
	    System.out.println("Tam: " + this.R.length);
	    System.out.println("Tam: " + this.D.length);*/
	    
	    
            pw.println("T,S,E,I0,I1,I2,I3,R,D");
            for(int i=0; i<S.length; i++) {
		//System.out.println("i: " + i);
                pw.print(t[i]+", ");
                pw.print(S[i]*pobTotal+", ");
                pw.print(E[i]*pobTotal+", ");
                pw.print(I0[i]*pobTotal+", ");
                pw.print(I1[i]*pobTotal+", ");
                pw.print(I2[i]*pobTotal+", ");
                pw.print(I3[i]*pobTotal+", ");
                pw.print(R[i]*pobTotal+", ");
                pw.println(D[i]*pobTotal+", ");
            }

            
        } catch(Exception e) {
            System.out.println("File Error " + e);
        } finally {
            try {
                if(archivo != null) {
                    System.out.println("Informacion guardada en: " + nombreArchivo);
		    //JOptionPane.showMessageDialog(null, "Informacion guardada en: " + nombreArchivo, "Información", JOptionPane.INFORMATION_MESSAGE);
                    archivo.close();
                }
            } catch(Exception e) {
                System.out.println("Exception" + e);
            }
        }
        
    }
    
    public double[] zeros(int n) {
        double[] vector = new double[n];
        for(int i=0; i<vector.length; i++) {
            vector[i]=0.0;
        }
        return vector;
    }
    
    public double[] llenaDias(int n) {
        double[] vector = new double[n];
        for(int i=0; i<vector.length; i++) {
            vector[i]=i+1;
        }
        return vector;
    }

    /**
     * @return the pobTotal
     */
    public int getPobTotal() {
	return pobTotal;
    }

    /**
     * @param pobTotal the pobTotal to set
     */
    public void setPobTotal(int pobTotal) {
	this.pobTotal = pobTotal;
    }

    /**
     * @return the region
     */
    public String getRegion() {
	return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
	this.region = region;
    }

    /**
     * @return the w
     */
    public double getW() {
	return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(double w) {
	this.w = w;
    }

    /**
     * @return the dias
     */
    public int getDias() {
	return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(int dias) {
	this.dias = dias;
    }

    /**
     * @return the A
     */
    public double[] getA() {
	return A;
    }

    /**
     * @param A the A to set
     */
    public void setA(double[] A) {
	this.A = A;
    }

    /**
     * @return the delta
     */
    public double[] getDelta() {
	return delta;
    }

    /**
     * @param delta the delta to set
     */
    public void setDelta(double[] delta) {
	this.delta = delta;
    }

    /**
     * @return the gamma
     */
    public double[] getGamma() {
	return gamma;
    }

    /**
     * @param gamma the gamma to set
     */
    public void setGamma(double[] gamma) {
	this.gamma = gamma;
    }

    /**
     * @return the sigma
     */
    public double[] getSigma() {
	return sigma;
    }

    /**
     * @param sigma the sigma to set
     */
    public void setSigma(double[] sigma) {
	this.sigma = sigma;
    }

    /**
     * @return the beta
     */
    public double[] getBeta() {
	return beta;
    }

    /**
     * @param beta the beta to set
     */
    public void setBeta(double[] beta) {
	this.beta = beta;
    }

    /**
     * @return the escenario
     */
    public int getEscenario() {
	return escenario;
    }

    /**
     * @param escenario the escenario to set
     */
    public void setEscenario(int escenario) {
	this.escenario = escenario;
    }

    /**
     * @return the S
     */
    public double[] getS() {
	return S;
    }

    /**
     * @param S the S to set
     */
    public void setS(double[] S) {
	this.S = S;
    }

    /**
     * @return the E
     */
    public double[] getE() {
	return E;
    }

    /**
     * @param E the E to set
     */
    public void setE(double[] E) {
	this.E = E;
    }

    /**
     * @return the I0
     */
    public double[] getI0() {
	return I0;
    }

    /**
     * @param I0 the I0 to set
     */
    public void setI0(double[] I0) {
	this.I0 = I0;
    }

    /**
     * @return the I1
     */
    public double[] getI1() {
	return I1;
    }

    /**
     * @param I1 the I1 to set
     */
    public void setI1(double[] I1) {
	this.I1 = I1;
    }

    /**
     * @return the I2
     */
    public double[] getI2() {
	return I2;
    }

    /**
     * @param I2 the I2 to set
     */
    public void setI2(double[] I2) {
	this.I2 = I2;
    }

    /**
     * @return the I3
     */
    public double[] getI3() {
	return I3;
    }

    /**
     * @param I3 the I3 to set
     */
    public void setI3(double[] I3) {
	this.I3 = I3;
    }

    /**
     * @return the R
     */
    public double[] getR() {
	return R;
    }

    /**
     * @param R the R to set
     */
    public void setR(double[] R) {
	this.R = R;
    }

    /**
     * @return the D
     */
    public double[] getD() {
	return D;
    }

    /**
     * @param D the D to set
     */
    public void setD(double[] D) {
	this.D = D;
    }

    /**
     * @return the t
     */
    public double[] getT() {
	return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(double[] t) {
	this.t = t;
    }

}

