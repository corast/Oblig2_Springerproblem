package main;
import java.util.Scanner;
import java.util.Random;

public class SpringerProblem {

    
    private static final int FREE = 0;
    private static final int USED = -1;
    private static final int OUT_OF_BOUND = -2;
    
    // Størrelse på sjakkbrett og 2D-tabell som lagrer sjakkbrettet
    private static int n, startX, startY;
    private static int S[][]; //n X n sjakkbrettet vårt.
    private static int traversedSquares = 0;
    //private static int traversedSquares1 = 6;
    
    /*
     * Springerproblemet består av å finne en måte å bevege en springer rundt på et sjakkbrett, fra en gitt utgangsposisjon,
     *  slik at den er innom alle ruter på brettet en og bare en gang.

Det skal lages et Java-program som løser springerproblemet for et n X n sjakkbrett. Størrelsen på sjakkbrettet, n,
 og springerens startposisjon skal oppgis av bruker. Det er tilstrekkelig at programmet finner kun én løsning av problemet. 
 Løsningen skal skrives ut som en sekvens av sjakktrekk/springerflytt, der hvert trekk angis på formen:

(ifra, jfra),(itil, jtil).
Merk at det ikke alltid vil finnes en løsning på springerproblemet, dette avhenger av brettstørrelse og utgangsposisjon.
 I så fall skal programmet skrive ut at det ikke fant noen løsning.
Oppgaven kan løses ved først å lage en rekursiv funksjon som genererer alle mulige "springerturer" med lengde n X n 
(også utenfor brettet). Man kan så legge inn avskjæring slik at springeren holder seg innenfor brettet 
og aldri oppsøker en rute to ganger. Begynn med små verdier av n når programmet skal testes.
     * */

    public static boolean FindPath(int i, int j)
    {
	// Leter rekursivt etter en vei gjennom labyrinten fra rute
	// (i,j) til rute (n-1,n-1).
    	
	// Bunn i rekursjonen: Ferdig hvis vi er i nedre høyre hjørne
	if (traversedSquares >= (n*n))
	// SPRINGERPROBLEMET må her ha et annet stoppkriterium
	{ 
	    // Markerer at siste rute i labyrinten ligger på veien som
	    // er funnet, og returnerer true
		System.out.println("HEI: " + traversedSquares);
		System.out.println("HEI2: " + i + " " + j);
	    return true;
	}
	
	// Markerer at rute (i,j) nå er oppsøkt
	S[i][j] = USED;
	
	// I SPRINGERPROBLEMET må vi her i tillegg lagre både antall
	// flytt som er gjort frem til nå, og hvike flytt som er gjort
	// for å komme hit.

	// Prøver alle fire mulige lovlige veier videre fra rute(i,j):
	// HØYRE, NED, VENSTRE, OPP

	// For å løse SPRINGERPROBLEMET, må koden her utvides til å
	// håndtere alle de åtte mulige lovlige stegene som en
	// springer kan ta fra rute (i,j) Lovlige vei en springer kan ta er 2 til høyre og en til venstre og alle konbinasjoner av dette
	//altså (i+2,j+1) (i+2,j-1) (i-2,j+1) (i-2,j-1) (i+1,j+2) (i+1,j-2) (i-1, j+2) (i-1, j-2) //alle 8 ulike kombinasjoner det er mulig å gå


	//System.out.println(traversedSquares);
	traversedSquares++;
	
	//(i+1, j-2) vei 1
		if(S[i+1][j-2] == FREE){
			if(FindPath(i+1,j-2)){
				S[i][j] = traversedSquares;
				traversedSquares--;
				return true;
			}
		}
	
	//(i+2, j-1) vei 2
	if(S[i+2][j-1] == FREE){
		if(FindPath(i+2,j-1)){
			S[i][j] = traversedSquares;
			traversedSquares--;
			return true;
		}
	}
	
	//i+2, j+1 vei 3
		if(S[i+2][j+1] == FREE){
			if(FindPath(i+2,j+1)){
				S[i][j] = traversedSquares;
				traversedSquares--;
				return true;
			}
		}
	
	//(i+1,j+2) vei 4
	if(S[i+1][j+2] == FREE){
		if(FindPath(i+1,j+2)){
			S[i][j] = traversedSquares;
			traversedSquares--;
			return true;
		}
	}
	
	//(i-1, j+2) vei 5
	if(S[i-1][j+2] == FREE){
		if(FindPath(i-1,j+2)){
			S[i][j] = traversedSquares;
			traversedSquares--;
			return true;
		}
	}
	
	//(i-2, j+1) vei 6
	if(S[i-2][j+1] == FREE){
		if(FindPath(i-2,j+1)){
			S[i][j] = traversedSquares;
			traversedSquares--;
			return true;
		}
	}
	
	//(i-2, j-1) vei 7
	if(S[i-2][j-1] == FREE){
		if(FindPath(i-2,j-1)){
			S[i][j] = traversedSquares;
			traversedSquares--;
			return true;
		}
	}
	
	//(i-1, j-2) vei 8
	if(S[i-1][j-2] == FREE){
		if(FindPath(i-1,j-2)){
			
			S[i][j] = traversedSquares;
			traversedSquares--;
			return true;
		}
	}
	
	traversedSquares--;
	
	// Hvis vi kommer hit i koden, fantes det ingen vei gjennom
	// labyrinten fra rute (i,j), returnerer false
	//fant ingen vei med springeren
	
	S[i][j] = FREE;
	
	return false;

	// I SPRINGERPROBLEMET må dette siste tilfellet, der vi ikke
	// finner noen lovlig løsning med start i rute (i,j),
	// behandles litt anderledes. I labyrinten er det ingen vits i
	// å gå tilbake til en rute der vi har vært før. I
	// springerproblemet er det ikke slik, der må vi nå markere at
	// ruten er blitt FREE igjen, slik at den kan brukes på nytt
	// i senere forsøk på å bygge ut en løsning. Øvrig
	// datastruktur som brukes til å lagre løsningen må også
	// oppdateres slik at dette steget som ikke ledet til løsning
	// blir fjernet.
    }

    /* Vi ber brukeren taste inn størrelsen på sjakk brettet, og en start posisjon
     * 
     * */
    
    public static void ScanInput(){
    	Scanner s = new Scanner(System.in);
    	//TODO try cach innput.
		// Leser størrelsen på labyrinten og prosentandel blokkerte ruter
		System.out.print("Størrelse på sjakk brett i n X n? Tast inn n: ");
		n = s.nextInt();
		
		System.out.print("Start posisjon i X retning på sjakk brettet? ");
		//startX = Double.parseDouble(System.console().readLine());	
		startX = s.nextInt();

		System.out.print("Start posisjon i Y retning på sjakk brettet? ");
		startY = s.nextInt(); 

		// Oppretter 2D-tabell som lagrer labyrinten
		S = new int[n+4][n+4];
    }
    
    public static void FillBoard(){
    	for (int i = 0; i < n+4; i++)
		    for (int j = 0; j < n+4; j++)
		    	if(i >= 2 && i < n+2 && j >=2 && j < n+2){
		    		S[i][j] = FREE;
		    	}else{
		    	    S[i][j] = OUT_OF_BOUND;
		    	}
		/*
		for (int i = 2; i < n-2; i++)
		    for (int j = 2; j < n-2; j++)
		    	S[i][j] = FREE;
		*/
    }
    
    public static void DrawBoard(){
    	for (int i = 0; i < n+4; i++)	
        {
		    for (int j = 0; j < n+4; j++)
		    {	
		    	System.out.print(S[i][j]);
		    	System.out.print(" | ");
		    }
		    System.out.println("");
        }
    	System.out.println("");
    }
    
    public static void main(String argv[])
    {
    	ScanInput();
		
		FillBoard();
		
		DrawBoard();
	
		
		// Leter etter vei fra øvre venstre hjørne
	
		boolean funnetVei = FindPath(startX+2, startY+2);
		
		// Skriver ut sjakk	brettet (og evt. funnet vei) 
		DrawBoard();

		System.out.println("");
		if (!funnetVei)
		    System.out.println("Fant ingen vei for springeren fra den startposisjonen");
	    }
}
