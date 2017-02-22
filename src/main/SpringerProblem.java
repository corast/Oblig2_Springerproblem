package main;
import java.util.Scanner;
import java.util.Random;

public class SpringerProblem {

    
    private static final int FREE = 0;
    private static final int USED = -1;
    private static final int OUT_OF_BOUND = -2;
    
    // St�rrelse p� sjakkbrett og 2D-tabell som lagrer sjakkbrettet
    private static int n, startX, startY;
    private static int S[][]; //n X n sjakkbrettet v�rt.
    private static int traversedSquares = 0;
    //private static int traversedSquares1 = 6;
    
    /*
     * Springerproblemet best�r av � finne en m�te � bevege en springer rundt p� et sjakkbrett, fra en gitt utgangsposisjon,
     *  slik at den er innom alle ruter p� brettet en og bare en gang.

Det skal lages et Java-program som l�ser springerproblemet for et n X n sjakkbrett. St�rrelsen p� sjakkbrettet, n,
 og springerens startposisjon skal oppgis av bruker. Det er tilstrekkelig at programmet finner kun �n l�sning av problemet. 
 L�sningen skal skrives ut som en sekvens av sjakktrekk/springerflytt, der hvert trekk angis p� formen:

(ifra, jfra),(itil, jtil).
Merk at det ikke alltid vil finnes en l�sning p� springerproblemet, dette avhenger av brettst�rrelse og utgangsposisjon.
 I s� fall skal programmet skrive ut at det ikke fant noen l�sning.
Oppgaven kan l�ses ved f�rst � lage en rekursiv funksjon som genererer alle mulige "springerturer" med lengde n X n 
(ogs� utenfor brettet). Man kan s� legge inn avskj�ring slik at springeren holder seg innenfor brettet 
og aldri opps�ker en rute to ganger. Begynn med sm� verdier av n n�r programmet skal testes.
     * */

    public static boolean FindPath(int i, int j)
    {
	// Leter rekursivt etter en vei gjennom labyrinten fra rute
	// (i,j) til rute (n-1,n-1).
    	
	// Bunn i rekursjonen: Ferdig hvis vi er i nedre h�yre hj�rne
	if (traversedSquares >= (n*n))
	// SPRINGERPROBLEMET m� her ha et annet stoppkriterium
	{ 
	    // Markerer at siste rute i labyrinten ligger p� veien som
	    // er funnet, og returnerer true
		System.out.println("HEI: " + traversedSquares);
		System.out.println("HEI2: " + i + " " + j);
	    return true;
	}
	
	// Markerer at rute (i,j) n� er opps�kt
	S[i][j] = USED;
	
	// I SPRINGERPROBLEMET m� vi her i tillegg lagre b�de antall
	// flytt som er gjort frem til n�, og hvike flytt som er gjort
	// for � komme hit.

	// Pr�ver alle fire mulige lovlige veier videre fra rute(i,j):
	// H�YRE, NED, VENSTRE, OPP

	// For � l�se SPRINGERPROBLEMET, m� koden her utvides til �
	// h�ndtere alle de �tte mulige lovlige stegene som en
	// springer kan ta fra rute (i,j) Lovlige vei en springer kan ta er 2 til h�yre og en til venstre og alle konbinasjoner av dette
	//alts� (i+2,j+1) (i+2,j-1) (i-2,j+1) (i-2,j-1) (i+1,j+2) (i+1,j-2) (i-1, j+2) (i-1, j-2) //alle 8 ulike kombinasjoner det er mulig � g�


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

	// I SPRINGERPROBLEMET m� dette siste tilfellet, der vi ikke
	// finner noen lovlig l�sning med start i rute (i,j),
	// behandles litt anderledes. I labyrinten er det ingen vits i
	// � g� tilbake til en rute der vi har v�rt f�r. I
	// springerproblemet er det ikke slik, der m� vi n� markere at
	// ruten er blitt FREE igjen, slik at den kan brukes p� nytt
	// i senere fors�k p� � bygge ut en l�sning. �vrig
	// datastruktur som brukes til � lagre l�sningen m� ogs�
	// oppdateres slik at dette steget som ikke ledet til l�sning
	// blir fjernet.
    }

    /* Vi ber brukeren taste inn st�rrelsen p� sjakk brettet, og en start posisjon
     * 
     * */
    
    public static void ScanInput(){
    	Scanner s = new Scanner(System.in);
    	//TODO try cach innput.
		// Leser st�rrelsen p� labyrinten og prosentandel blokkerte ruter
		System.out.print("St�rrelse p� sjakk brett i n X n? Tast inn n: ");
		n = s.nextInt();
		
		System.out.print("Start posisjon i X retning p� sjakk brettet? ");
		//startX = Double.parseDouble(System.console().readLine());	
		startX = s.nextInt();

		System.out.print("Start posisjon i Y retning p� sjakk brettet? ");
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
	
		
		// Leter etter vei fra �vre venstre hj�rne
	
		boolean funnetVei = FindPath(startX+2, startY+2);
		
		// Skriver ut sjakk	brettet (og evt. funnet vei) 
		DrawBoard();

		System.out.println("");
		if (!funnetVei)
		    System.out.println("Fant ingen vei for springeren fra den startposisjonen");
	    }
}
