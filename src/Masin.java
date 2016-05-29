//Enigma masin, valmis 18.12.14, Diana Algma

import java.util.InputMismatchException;
import java.util.Scanner;


public class Masin {

	public static void main(String[] args) {
		//juhtmed, näiteks esimene 3 tähendab, et A-st läheb juhe D-sse (lugemine 0-st).
		int[] juhtmedI = {3,12,19,22,18,8,11,17,20,24,16,13,10,5,4,9,2,0,25,1,15,6,23,14,7,21};
		int[] juhtmedII = {7,16,25,6,15,9,19,12,14,1,11,13,2,8,5,3,24,0,22,21,4,20,18,17,10,23};
		int[] juhtmedIII = {20,16,13,19,11,18,25,5,12,17,4,7,3,15,23,10,8,1,21,24,6,9,2,22,14,0};
		int[] juhtmedP = {4,9,12,25,0,11,24,23,21,1,22,5,2,17,16,20,14,13,19,18,15,8,10,7,6,3};
		//loob rootorid ja peegeldi
		Rootor I = new Rootor("I", juhtmedI, 5);
		Rootor II = new Rootor("II", juhtmedII, 7);
		Rootor III = new Rootor("III", juhtmedIII, 16);
		Rootor P = new Rootor("P", juhtmedP, 0);
		
		//scanner seadete ja teksti küsimiseks
	    Scanner scan = new Scanner(System.in);
	    String edasi = "j";
	    while(edasi.toLowerCase().equals("j")||edasi.toLowerCase().equals("jah")){
	    	//rootorite seaded
	    	int esimeneSeis;
	    	int teineSeis;
	    	int kolmasSeis;
	    	try {
			    System.out.print("Sisesta esimese rootori seade (arv 0-25): ");
				esimeneSeis = scan.nextInt();
				while(esimeneSeis<0||esimeneSeis>25){
					System.out.print("Seade peab olema vahemikus 0-25, anna õige seade: ");
					esimeneSeis = scan.nextInt();
				}
			    System.out.print("Sisesta teise rootori seade (arv 0-25): ");
			    teineSeis = scan.nextInt();
				while(teineSeis<0||teineSeis>25){
					System.out.print("Seade peab olema vahemikus 0-25, anna õige seade: ");
					teineSeis = scan.nextInt();
				}
				System.out.print("Sisesta kolmanda rootori seade (arv 0-25): ");
				kolmasSeis = scan.nextInt();
				while(kolmasSeis<0||kolmasSeis>25){
					System.out.print("Seade peab olema vahemikus 0-25, anna õige seade: ");
					kolmasSeis = scan.nextInt();
				}
				scan.nextLine();	//et sisestada saaks järgmisena teksti
	    	} catch (InputMismatchException e) {
	    		//kui sisend oli vigane, alustatakse uuesti esimese seade küsimisest
				System.out.println("Viga seadete sisestamisel.");
				scan.nextLine();
				continue;
			}
	    	//küsitakse tekst, mida "masinast läbi lasta"
			System.out.println("Sisesta tekst, mida tahad ðifreerida (täpitähti ei loeta):");
			String tekst = scan.nextLine();
			tekst = tekst.toUpperCase().replaceAll("[^a-zA-Z]", "");
			
			//määrab rootorite järjestuse
			Rootor esimeneR = I;
			Rootor teineR = II;
			Rootor kolmasR = III;
			
			//sätib rootorid õigesse asendisse
			esimeneR.setSeis(esimeneSeis);
			teineR.setSeis(teineSeis);
			kolmasR.setSeis(kolmasSeis);
			
			//ðifreerib antud teksti, tulemuse prindib jooksvalt välja ja salvestab sõnena
			System.out.println("Ðifreeritud:");
			String tulemus = "";
			for(int i = 0; i<tekst.length(); i++){
				//pöörab rootorit (või rootoreid)
				esimeneR.pööra(teineR, kolmasR);
				char x = tekst.charAt(i); 		//võetakse järjest tähti antud tekstist
				int a = esimeneR.sisesta(x);	//täht läbib esimese rootori
				int b = teineR.edasta(a);		//teise rootori
				int c = kolmasR.edasta(b);		//kolmanda rootori
				int d = P.edasta(c);			//peegeldatakse tagasi
				int e = kolmasR.tagasta(d);		//läbib uuesti kolmanda rootori
				int f = teineR.tagasta(e);		//teise rootori
				char g = esimeneR.väljasta(f);	//läbib esimese rootori ja tagastab tähe
				System.out.print(g);			//täht prinditakse
				tulemus = tulemus + g;			//täht lisatakse tulemuse sõnesse
			}
			//seaded taastatakse ja sama protseduur tulemusega
			System.out.println("\nDeðifreeritud:");
			String tagasi = tulemus;
			esimeneR.setSeis(esimeneSeis);
			teineR.setSeis(teineSeis);
			kolmasR.setSeis(kolmasSeis);
			for(int i = 0; i<tagasi.length(); i++){
				esimeneR.pööra(teineR, kolmasR);
				char x = tagasi.charAt(i); 
				int a = esimeneR.sisesta(x);
				int b = teineR.edasta(a);
				int c = kolmasR.edasta(b);
				int d = P.edasta(c);
				int e = kolmasR.tagasta(d);
				int f = teineR.tagasta(e);
				char g = esimeneR.väljasta(f);
				System.out.print(g);
			}
			
			//antakse võimalus masinat edasi kasutada, kui öeldakse "j" või "jah", hakatakse uuesti
			//seadeid ja teksti küsima.
			System.out.print("\nKas soovid uuesti? (j/e) ");
			edasi = scan.nextLine(); 
	    }

		scan.close();
	}

}
