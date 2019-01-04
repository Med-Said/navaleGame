package navale;

import java.util.Scanner;

public class Run{
	static int taillePlateau;
	//1
	public final static char VIDE = 'v', JOUEURA = 'a', JOUEURB = 'b', LES2JOUEURS = '2';
	
	//2 la methode afficher()
	 public static void afficher(char tabChar[][]){
		 for(int i = 1; i<=tabChar.length;i++){//pour l'affichage de la marge superieur (1,2 ...)
			 System.out.print("\t"+i);
		 }
		 System.out.println("\n");
		char conteurVertical = 'A';//pour afficher les lettres en marge gauche A,B ...
		 for(char sousTabChar[] : tabChar){// pour boucler  le table (grand table)
			 int i =0;//pour gerer les tableaux a l'aide des indices
			 for(char lettre : sousTabChar){
				 switch(lettre){
				 case JOUEURA : lettre = 'A';break;
				 case JOUEURB : lettre = 'B';break;
				 case LES2JOUEURS : lettre = '2';break;
				 case VIDE : lettre = ' ';break;
				 //default : lettre = ' ';
				 }
				 sousTabChar[i]=lettre;
				 i++;//pour arriver a l'indice suivant 
			 }
			 System.out.print(conteurVertical);//pour l'affichage de la marge gauche A B ...
			 for(char c : sousTabChar){//lister horizentalment les elements du chaque sousTabChar
				 System.out.print("\t" + c);
			 }
			 conteurVertical = (char) (conteurVertical + 1);//utilisation du code ascii pour aller a la lettre suivante(marge gauche)
			 System.out.println("\n");
		} 
	 }
	//mes propres methodes(non demander dans l'exercice)	 
	 //la methode gagner() prend un joueur et la palteau du jeu et return true si le joueur qui joue gagne false sinon
	 public static boolean gagner(char j, char tab[][]){
			boolean ganTab = true;
			for(char sousTab[] : tab){
				boolean ganSousTab = true;
				if (j == 'a'){
					for(char c : sousTab){
						ganSousTab =  ganSousTab && (c==' ' || c=='A');//true si le tableau ne contien que les espaces ou la lettre representant le joueur adveseur
					}
					//true si le tableau donnee en parametre ne contien que les espaces ou la lettre representant le joueur adveseur, false sinon
					ganTab = ganTab && ganSousTab;
				}
				else if(j == 'b'){
					for(char c : sousTab){
						ganSousTab =  ganSousTab && (c==' ' || c=='B');
					}
					ganTab = ganTab && ganSousTab; 
				}
			}
			return ganTab;
		}
	 
	 //3 la methode placerBateauxAleatoirement()
	 public  static void placerBateauxAleatoirement(char joueur, char tab[][]){
		 int i=0,j=0;
		 for(int k = 0; k<taillePlateau; k++){ 
			 if(tab[i][j]==' '){// on verifie si la cases est vide ou non(s'elle n'est pas  vide on passe a else 
				if(joueur == 'a')
					tab[i][j] = 'A'; 
				else
					tab[i][j]='B';
			 }
			 else{//dans cette else on countinue a chercher d'une case vide a but d'eviter la superposition des bateau du meme utilisateur
				 do{
					 i = (int) (Math.random()*taillePlateau);
					 j = (int) (Math.random()*taillePlateau);
				 }while(tab[i][j]!=' ');
				 if(joueur == 'a')
					 tab[i][j] = 'A'; 
				 else
					 tab[i][j]= 'B';
			 }
		 }
	 }
	 
	 //4 la methode placerBateaux()
	 //cette methode est sumilaire a la methode placerBateauxAleatoirement mais ici on donne a l'utilisateur le possibilite de choisire l'emplacement
	 public static void placerBateaux(char joueur, char tab[][]){
		 //les indices d'indexation 
		 int i=0,j=0;
		 for(int l = 0; l<taillePlateau; l++){
			 System.out.println("ligne: ");
			 i = scanf.nextInt();
			 System.out.println("colonne: ");
			 j = scanf.nextInt();
			 
			 if(tab[i-1][j-1] == 'A' || tab[i-1][j-1] == 'A'){
				 tab[i-1][j-1] = LES2JOUEURS;
			 }
			 else if (tab[i-1][j-1] == 'v' || tab[i-1][j-1] == ' ' || tab[i-1][j-1] == 0){
				 if(joueur =='a')
					 tab[i-1][j-1] = 'A';
				 else
					 tab[i-1][j-1] = 'B';
			 }
			 else{//en cas normal on doit pas arriver a  cette else
				 tab[i-1][j-1]='?';
			 }
		 }
	 }
	 
	 //5 la methode jouer()
	 public static boolean jouer(char joueur, char tab[][]){// ici le tableau donnee en parametre est suppose remplu
		 int i=0,j=0;
		 System.out.println("saisissez les coordonnees de la case torpillée");
		 System.out.println("ligne: ");
		 i = scanf.nextInt();
		 System.out.println("colonne: ");
		 j = scanf.nextInt();
		 
		 //les conditions qui verifient qu'un bateau d'adverseur est touche
		 if( ((tab[i-1][j-1] == 'A' && joueur == 'b') || (tab[i-1][j-1] =='B' && joueur == 'a')) /*&& i<tab.length && j<tab.length*/){
			 System.out.println("Coulé");
			 tab[i-1][j-1] = ' ';//mise a jour du plateau de jeu
		 }//les conditons qui  verifient q'un torpille est dans l'eau
		 else if( (tab[i-1][j-1] == 'A' && joueur == 'a') || (tab[i-1][j-1] == 'B' && joueur == 'b') || tab[i-1][j-1] == ' '  || tab[i-1][j-1] == 'v' || tab[i-1][j-1] == 0 ){
			 System.out.println("Dans l'eau");
		 }
		 
		 //le cas ou les deux joueurs ont des bateaux sur la meme case
		 else if(tab[i-1][j-1] == '2' && joueur == 'a'){
			 tab[i-1][j-1] = 'A';
			 System.out.println("Coulé");
		 }
		 else if(tab[i-1][j-1] == '2' && joueur == 'b'){
			 tab[i-1][j-1] = 'B';
			 System.out.println("Coulé");
		 }
		 return gagner(joueur,tab);
	 }
	 
	 static Scanner scanf = new Scanner(System.in);
		
		// la methode main()
		public static void main(String[] args){
			//int taillePlateau=0;
			do{//une boucle pour l'assurance que le nombre des cases est entre ( 2 et 9)
				System.out.println("quelle taille voulez-vous pour le plateau de jeu (entre 2 et 9): ");
				 taillePlateau = scanf.nextInt();
			}while(taillePlateau<2 || 9<taillePlateau);
					
			char[][] tabChar = new char[taillePlateau][taillePlateau];//reservation de la memoire necessaire
			
			 //remplissage des cases avec la valeur VIDE('v')
			int j=0;
			 for(char sousTabChar[] : tabChar){
				 j=0;
				 for(j=0;j<sousTabChar.length;j++){
					 sousTabChar[j] = VIDE;
				 }
			 }
			 
			 afficher(tabChar);
			 placerBateauxAleatoirement('a',tabChar);
			 placerBateauxAleatoirement('b',tabChar);
			 
//			 System.out.println("joueur A positione tes bateux");
//			 placerBateaux('a', tabChar);
//			 System.out.println("joueur B positione tes bateux");
//			 placerBateaux('b', tabChar);
			 afficher(tabChar);//on affiche le blateau du jeu seulement pout tester
			  int i=0;
			 while(i==0){//boucle infinit(mais pas de panique on a des break a l'interieur 
				 System.out.println("joueur A: ");
				 jouer('a',tabChar);
				 afficher(tabChar);
				 if(gagner('a',tabChar)){
					 System.out.println("le GAGNANT : JOUEURA");
					 break;					 
				 }

				 System.out.println("joueur B: ");
				 jouer('b',tabChar);
				 afficher(tabChar);
				 if(gagner('b',tabChar)){
					 System.out.println("le GAGNANT : JOUEURB");
					 break;					 
				 }
			 }
		}
}
