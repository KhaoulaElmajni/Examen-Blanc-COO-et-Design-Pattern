
# <strong style="color:blue; opacity: 0.80">Examen Blanc COO et Design Pattern </strong>:mortar_board::computer: 
# <span style="color:green "> 1.Présentation de l'activité pratique</span>
 * <strong style="color:dark">On souhaite concevoir et développer une application qui permet manipuler des dessins. 
Un Dessin contient un ensemble de figures géométriques qui peuvent être soit des rectangles ou des cercles.
Pour chaque figure on souhaite calculer son périmètre et sa surface et on souhaite définir une méthode qui permet de dessiner la figure (Afficher l’état de la figure). 
    Un cercle est défini par son centre qui est un point et par son rayon R. Un rectangle est défini par un point qui représente le coin supérieur gauche, sa largeur L et sa hauteur H. Un point est définit par ses coordonnées X et Y. Pour le cercle, sa surface veut π * R^2 alors sa surface vaut 2 * π *R. Pour le rectangle, sa surface veut L * H alors sa surface vaut 2 *(L+H). </span>
## <span style="color:#66ff66"> Entités et règles de gestion : :label:</span>
L’application devra gérer 6 entités. 
Les entités utilisées dans l’application sont : 
* * * 

```java=10
public class Dessin implements Serializable {
    private List<Figure> figures=new ArrayList<>();
    private transient IStrategy strategy=new StrategyImpl1();

    public Figure ajouterFigure(Figure figure){
        figures.add(figure);
        return figure;
    }

    public void supprimerFigure(Figure figure){
        figures.remove(figure);
    }

    public void serialiser(String fileName) throws Exception {
        File file=new File(fileName);
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
    }
    public void effectuerStrategyTraitement(){
        this.strategy.traiter(figures);
    }

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void afficher(){
        for (Figure figure:figures){
            figure.afficher();
        }
    }

    public static Dessin desserialiser(String fileName) throws Exception {
        File file=new File(fileName);
        FileInputStream fileInputStream=new FileInputStream(file);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        Dessin dessin=(Dessin) objectInputStream.readObject();
        objectInputStream.close();
        return dessin;
    }
}
```

```java=10
public abstract class Figure implements Serializable, Observer {
    protected int epaisseurContour;
    protected int couleurContour;
    protected int couleurRemplissage;
    protected int niveau;
    public abstract double calculSurface();
    public abstract double calculPerimetre();
    public abstract void afficher();

    @Override
    public String toString() {
        return
                "epaisseurContour = " + epaisseurContour +
                ", couleurContour = " + couleurContour +
                ", couleurRemplissage = " + couleurRemplissage ;
    }

    public int getEpaisseurContour() {
        return epaisseurContour;
    }

    public void setEpaisseurContour(int epaisseurContour) {
        this.epaisseurContour = epaisseurContour;
    }

    public int getCouleurContour() {
        return couleurContour;
    }

    public void setCouleurContour(int couleurContour) {
        this.couleurContour = couleurContour;
    }

    public int getCouleurRemplissage() {
        return couleurRemplissage;
    }

    public void setCouleurRemplissage(int couleurRemplissage) {
        this.couleurRemplissage = couleurRemplissage;
    }

    @Override
    public void update(Parametrage parametrage) {
        this.epaisseurContour =parametrage.getEpaisseurContour();
        this.couleurContour=parametrage.getCouleurContour();
        this.couleurRemplissage=parametrage.getCouleurRemplissage();
    }
    protected String tabs(){
        String tabs="";
        for (int i = 0; i <niveau ; i++) {
            tabs+="\t";
        }
        return tabs;
    }
}
```
```java=10
public abstract class Figure implements Serializable, Observer {
    protected int epaisseurContour;
    protected int couleurContour;
    protected int couleurRemplissage;
    protected int niveau;
    public abstract double calculSurface();
    public abstract double calculPerimetre();
    public abstract void afficher();

    @Override
    public String toString() {
        return
                "epaisseurContour = " + epaisseurContour +
                ", couleurContour = " + couleurContour +
                ", couleurRemplissage = " + couleurRemplissage ;
    }

    public int getEpaisseurContour() {
        return epaisseurContour;
    }

    public void setEpaisseurContour(int epaisseurContour) {
        this.epaisseurContour = epaisseurContour;
    }

    public int getCouleurContour() {
        return couleurContour;
    }

    public void setCouleurContour(int couleurContour) {
        this.couleurContour = couleurContour;
    }

    public int getCouleurRemplissage() {
        return couleurRemplissage;
    }

    public void setCouleurRemplissage(int couleurRemplissage) {
        this.couleurRemplissage = couleurRemplissage;
    }

    @Override
    public void update(Parametrage parametrage) {
        this.epaisseurContour =parametrage.getEpaisseurContour();
        this.couleurContour=parametrage.getCouleurContour();
        this.couleurRemplissage=parametrage.getCouleurRemplissage();
    }
    protected String tabs(){
        String tabs="";
        for (int i = 0; i <niveau ; i++) {
            tabs+="\t";
        }
        return tabs;
    }
}
```

```java=10
public class Cercle extends Figure {
    private Point centre;
    private double rayon;

    public Cercle(Point centre, double rayon) {
        this.centre = centre;
        this.rayon = rayon;
    }

    @Override
    public double calculSurface() {
        return Math.PI*rayon*rayon;
    }

    @Override
    public double calculPerimetre() {
        return 2*Math.PI*rayon;
    }

    @Override
    public void afficher() {
        System.out.println(tabs()+"Cercle ==> ( X = "
                +centre.getX()+", Y = "+centre.getY()+", Rayon = "+rayon+","+super.toString()+")");
    }
}

```

```java=10
public class GroupeFigures extends Figure{
    private List<Figure> figures=new ArrayList<>();

    public Figure ajouterFigure(Figure figure){
        figure.niveau=this.niveau+1;
        figures.add(figure);
        return figure;
    }


    @Override
    public double calculSurface() {
        double surfaceTotal = 0.0;

        for (Figure f : figures) {
            surfaceTotal+=f.calculSurface();
        }

        return surfaceTotal;
    }

    @Override
    public double calculPerimetre() {
        double perimetreTotal = 0.0;

        for (Figure f : figures) {
            perimetreTotal+=f.calculPerimetre();
        }

        return perimetreTotal;
    }

    @Override
    public void afficher() {
        System.out.println(tabs()+"Affichage du Groupe : ==> {");
        for (Figure figure :figures){
            figure.afficher();
        }
        System.out.println("}");
    }

    public List<Figure> getFigures() {
        return figures;
    }
}

```

```java=10
public class Point implements Serializable {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}


```


## <span style="color:#66ff66">  les fonctionnalités de l'application :label: </span>
Chaque figure est définit également par des attributs de type entiers comme l’épaisseur du contour, la couleur du contour et la couleur de remplissage.
Dans ce modèle, on voudrait aussi respecter les critères suivants :


### 1. <span style="color:#001a33">Donner la possibilité de Créer un groupe de figures. Chaque groupe peut contenir d’autres groupes..</span>
### 2. <span style="color:#001a33">Nous introduisons un Objet Paramétrage dont l’état est défini par attributs par défaut comme l’épaisseur du contour, la couleur du contour et la couleur de remplissage. On voudrait qu’à chaque fois que l’état de cet objet change, toutes les figures doivent être notifiées pour ce mis à jour.</span>
### 3. <span style="color:#001a33">Pour l’objet dessin, on voudrait définir les opérations suivantes :
##### Une méthode « traiter » qui permet de traiter le contenu du dessin en utilisant une famille d’algorithmes qui sont interchangeables dynamiquement. Ce signifie qu’au moment de l’exécution, l’objet dessin pour changer d’algorithme avec un autre de la même famille. Chaque algorithme peut évoluer indépendamment de la classe dessin qui les utilise.
##### Les méthodes qui permettent d’ajouter et supprimer des figures du dessin.
##### Une méthode qui permet d’afficher toutes les figures du dessin.
##### Une méthode qui permet de sérialiser le dessin dans un fichier binaire.</span>


# <span style="color:green">3.Les Design Pattern utilisées</span>
 #### <span style="color:#0036ad"> 1 Composite</span>
 * <strong style="color:dark">Organiser les objets en structure arborescente afin de représenter une hiérarchie.
Permettre à la partie cliente de manipuler un objet unique et un objet composé de la
même manière <strong style="color:dark">.

*voir également à propos* [Composite](https://www.java.com/fr/):link: 


 #### <span style="color:#0036ad"> 2 Strategy</span>
 * Définir une famille d’algorithmes, et encapsuler chacun et les rendre interchangeables tout en assurant que chaque algorithme puisse évoluer indépendamment des clients qui l’utilisent<strong style="color:dark">.
    

*voir également à propos de [Strategy](https://spring.io/projects/spring-data-jpa) :link: 

#### <span style="color:#0036ad"> 4 Observer.</span>
 * Le pattern Observer définit une relation entre les objets de type un à plusieurs, de façon que, lorsqu’un objet change d’état, tous ce qui en dépendent en soient informés et soient mis à jour automatiquement<strong style="color:dark">.
*voir également à propos* [Observer](https://devdocs.io/css/) :link: 


 
## <span style="color:green ">4.Structure du projet</span>
![](https://i.imgur.com/lSeMW0s.png)



 ## <span style="color:green ">5.CONCEPTION & ANALYSES</span>
 * ###### <strong style="color:red; opacity: 0.80">Diagramme de classe </strong>
> Diagramme de classe [color=#fd837b]
![](https://i.imgur.com/QMMNcZN.png)

  ---



 ## <strong style="color: green; opacity: 0.80" >6.comment ça marche ?</strong>
    
![](https://i.imgur.com/K4zPgFw.png)



    
* <strong style="color: dark ; opacity: 0.80">Enfin nous tenons à remercier le seul et unique, notre professeur Mr YOUSFI Mohamed *Docteur & professeur à l'ENSET MEDIA* pour son soutien et son encouragement envers nous, aussi pour nous avoir donné cette opportunité d'améliorer nos compétences et de connaître les nouvelles technologies comme celles qui nous avons travaillé.

*voir également à propos* Mr [YOUSSFI Mohamed](https://www.linkedin.com/in/mohamed-youssfi-3ab0811b/)
</strong>

> Created by :[name=ELMAJNI KHAOULA]
[time=Mon,2022,10,20][color=#EF0101]
>*voir également à propos de moi* [ELMAJNI Khaoula](https://www.linkedin.com/in/khaoula-elmajni/)