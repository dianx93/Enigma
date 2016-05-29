//klass rootorite ja peegeldi jaoks

public class Rootor {
	static String tähed = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String nimi;
	private int ringseis;	//täht (järjekorranumber), mille juures saab rootoril ring täis
	private int seis;		//rootori asend, ehk mitmes täht on esimesel kohal
	private int[] juhtmed;	//juhtmed (Masina klassis seletatud)
	
	public Rootor(String nimi, int[] juhtmed, int ringseis) {
		super();
		this.nimi = nimi;
		this.juhtmed = juhtmed;
		this.ringseis = ringseis;
		this.seis = 0;
	}

	public int getRingseis() {
		return ringseis;
	}
	
	public int getSeis() {
		return seis;
	}

	public void setSeis(int seis) {
		if(nimi != "P"){
			this.seis = seis%26;
		}
	}
	
	//pöörab esimest rootorit, vajadusel ka teist ja kolmandat
	public void pööra(Rootor r2, Rootor r3){
		seis = (seis+1)%26;
		if(seis == ringseis){
			r2.setSeis((r2.getSeis()+1)%26);
			if(r2.getSeis()==r2.getRingseis()){
				r3.setSeis((r3.getSeis()+1)%26);
			}
		}
	}
	
	//tähe sisestamine rootorisse, tagastab, mitmendale kohale saabub signaal
	public int sisesta(char c){
		return (juhtmed[(seis + tähed.indexOf(c))%26]-seis+26)%26;
	}

	//numbri edastamine rootorisse, antakse signaali saabumiskoht ja väljastatakse järgmise signaali koht
	public int edasta(int nr){
		int a = juhtmed[(seis + nr)%26];
		return (26+a-seis)%26;
	}
	
	//signaal peegeldatakse tagasi, kasutatakse peegeldi puhul, tagastab jälle signaali asukoha
	public int peegelda(int nr){
		return juhtmed[nr];
	}
	
	//signaal liigub läbi rootori tagasi
	public int tagasta(int nr){
		for(int i = 0; i<juhtmed.length; i++){
			if(juhtmed[i]==(seis + nr)%26){
				return (26+i-seis)%26;
			}
		}
		System.out.println("\nViga");
		return -1;
	}
	
	//signaal liigub viimasesse (esimesse) rootorisse, väljastatakse täht
	public char väljasta(int nr){
		for(int i = 0; i<juhtmed.length; i++){
			if(juhtmed[i]==(nr+seis)%26){
				return tähed.charAt((26+i-seis)%26);
			}
		}
		System.out.println("\nViga");
		return tähed.charAt(0);
	}
	
}
