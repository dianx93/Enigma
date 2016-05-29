//Enigma masin, valmis 18.12.14, Diana Algma

import java.util.InputMismatchException;
import java.util.Scanner;


public class Masin {

	public static void main(String[] args) {
		//juhtmed, n�iteks esimene 3 t�hendab, et A-st l�heb juhe D-sse (lugemine 0-st).
		int[] juhtmedI = {3,12,19,22,18,8,11,17,20,24,16,13,10,5,4,9,2,0,25,1,15,6,23,14,7,21};
		int[] juhtmedII = {7,16,25,6,15,9,19,12,14,1,11,13,2,8,5,3,24,0,22,21,4,20,18,17,10,23};
		int[] juhtmedIII = {20,16,13,19,11,18,25,5,12,17,4,7,3,15,23,10,8,1,21,24,6,9,2,22,14,0};
		int[] juhtmedP = {4,9,12,25,0,11,24,23,21,1,22,5,2,17,16,20,14,13,19,18,15,8,10,7,6,3};
		//loob rootorid ja peegeldi
		Rootor I = new Rootor("I", juhtmedI, 5);
		Rootor II = new Rootor("II", juhtmedII, 7);
		Rootor III = new Rootor("III", juhtmedIII, 16);
		Rootor P = new Rootor("P", juhtmedP, 0);
		
		//scanner seadete ja teksti k�simiseks
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
					System.out.print("Seade peab olema vahemikus 0-25, anna �ige seade: ");
					esimeneSeis = scan.nextInt();
				}
			    System.out.print("Sisesta teise rootori seade (arv 0-25): ");
			    teineSeis = scan.nextInt();
				while(teineSeis<0||teineSeis>25){
					System.out.print("Seade peab olema vahemikus 0-25, anna �ige seade: ");
					teineSeis = scan.nextInt();
				}
				System.out.print("Sisesta kolmanda rootori seade (arv 0-25): ");
				kolmasSeis = scan.nextInt();
				while(kolmasSeis<0||kolmasSeis>25){
					System.out.print("Seade peab olema vahemikus 0-25, anna �ige seade: ");
					kolmasSeis = scan.nextInt();
				}
				scan.nextLine();	//et sisestada saaks j�rgmisena teksti
	    	} catch (InputMismatchException e) {
	    		//kui sisend oli vigane, alustatakse uuesti esimese seade k�simisest
				System.out.println("Viga seadete sisestamisel.");
				scan.nextLine();
				continue;
			}
	    	//k�sitakse tekst, mida "masinast l�bi lasta"
			System.out.println("Sisesta tekst, mida tahad �ifreerida (t�pit�hti ei loeta):");
			String tekst = scan.nextLine();
			tekst = tekst.toUpperCase().replaceAll("[^a-zA-Z]", "");
			
			//m��rab rootorite j�rjestuse
			Rootor esimeneR = I;
			Rootor teineR = II;
			Rootor kolmasR = III;
			
			//s�tib rootorid �igesse asendisse
			esimeneR.setSeis(esimeneSeis);
			teineR.setSeis(teineSeis);
			kolmasR.setSeis(kolmasSeis);
			
			//�ifreerib antud teksti, tulemuse prindib jooksvalt v�lja ja salvestab s�nena
			System.out.println("�ifreeritud:");
			String tulemus = "";
			for(int i = 0; i<tekst.length(); i++){
				//p��rab rootorit (v�i rootoreid)
				esimeneR.p��ra(teineR, kolmasR);
				char x = tekst.charAt(i); 		//v�etakse j�rjest t�hti antud tekstist
				int a = esimeneR.sisesta(x);	//t�ht l�bib esimese rootori
				int b = teineR.edasta(a);		//teise rootori
				int c = kolmasR.edasta(b);		//kolmanda rootori
				int d = P.edasta(c);			//peegeldatakse tagasi
				int e = kolmasR.tagasta(d);		//l�bib uuesti kolmanda rootori
				int f = teineR.tagasta(e);		//teise rootori
				char g = esimeneR.v�ljasta(f);	//l�bib esimese rootori ja tagastab t�he
				System.out.print(g);			//t�ht prinditakse
				tulemus = tulemus + g;			//t�ht lisatakse tulemuse s�nesse
			}
			//seaded taastatakse ja sama protseduur tulemusega
			System.out.println("\nDe�ifreeritud:");
			String tagasi = tulemus;
			esimeneR.setSeis(esimeneSeis);
			teineR.setSeis(teineSeis);
			kolmasR.setSeis(kolmasSeis);
			for(int i = 0; i<tagasi.length(); i++){
				esimeneR.p��ra(teineR, kolmasR);
				char x = tagasi.charAt(i); 
				int a = esimeneR.sisesta(x);
				int b = teineR.edasta(a);
				int c = kolmasR.edasta(b);
				int d = P.edasta(c);
				int e = kolmasR.tagasta(d);
				int f = teineR.tagasta(e);
				char g = esimeneR.v�ljasta(f);
				System.out.print(g);
			}
			
			//antakse v�imalus masinat edasi kasutada, kui �eldakse "j" v�i "jah", hakatakse uuesti
			//seadeid ja teksti k�sima.
			System.out.print("\nKas soovid uuesti? (j/e) ");
			edasi = scan.nextLine(); 
	    }

		scan.close();
	}

}
