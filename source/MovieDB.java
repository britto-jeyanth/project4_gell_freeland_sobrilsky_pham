
/*******************************************************************************
 * @file  MovieDB.java
 *
 * @author   John Miller
 */

import static java.lang.System.out;

/*******************************************************************************
 * The MovieDB class makes a Movie Database.  It serves as a template for making
 * other databases.  See "Database Systems: The Complete Book", second edition,
 * page 26 for more information on the Movie Database schema.
 */
class MovieDB
{
    /***************************************************************************
     * Main method for creating, populating and querying a Movie Database.
     * @param args  the command-line arguments
     */
    public static void main (String [] args)
    {
        out.println ();

        IndexTable movie = new IndexTable ("movie", "title year length genre studioName producerNo",
                                          "String Integer Integer String String Integer", "title year");

        IndexTable cinema = new IndexTable ("cinema", "title year length genre studioName producerNo",
                                            "String Integer Integer String String Integer", "title year");

        IndexTable movieStar = new IndexTable ("movieStar", "name address gender birthdate",
                                                  "String String Character String", "name");

        IndexTable starsIn = new IndexTable ("starsIn", "movieTitle movieYear starName",
                                              "String Integer String", "movieTitle movieYear starName");

        IndexTable movieExec = new IndexTable ("movieExec", "certNo name address fee",
                                                  "Integer String String Float", "certNo");

        IndexTable studio = new IndexTable ("studio", "name address presNo",
                                            "String String Integer", "name");

        Comparable [] film0 = { "Star_Wars", 1977, 124, "sciFi", "Fox", 12345 };
        Comparable [] film1 = { "Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345 };
        Comparable [] film2 = { "Rocky", 1985, 200, "action", "Universal", 12125 };
        Comparable [] film3 = { "Rambo", 1978, 100, "action", "Universal", 32355 };
        out.println ();
        movie.insert (film0);
        movie.insert (film1);
        movie.insert (film2);
        movie.insert (film3);
        movie.print ();

        Comparable [] film4 = { "Galaxy_Quest", 1999, 104, "comedy", "DreamWorks", 67890 };
        out.println ();
        cinema.insert (film2);
        cinema.insert (film3);
        cinema.insert (film4);
        cinema.print ();

        Comparable [] star0 = { "Carrie_Fisher", "Hollywood", 'F', "9/9/99" };
        Comparable [] star1 = { "Mark_Hamill", "Brentwood", 'M', "8/8/88" };
        Comparable [] star2 = { "Harrison_Ford", "Beverly_Hills", 'M', "7/7/77" };
        out.println ();
        movieStar.insert (star0);
        movieStar.insert (star1);
        movieStar.insert (star2);
        movieStar.print ();

        Comparable [] cast0 = { "Star_Wars", 1977, "Carrie_Fisher" };
        out.println ();
        starsIn.insert (cast0);
        starsIn.print ();

        Comparable [] exec0 = { 9999, "S_Spielberg", "Hollywood", 10000.00 };
        out.println ();
        movieExec.insert (exec0);
        movieExec.print ();

        Comparable [] studio0 = { "Fox", "Los_Angeles", 7777 };
        Comparable [] studio1 = { "Universal", "Universal_City", 8888 };
        Comparable [] studio2 = { "DreamWorks", "Universal_City", 9999 };
        out.println ();
        studio.insert (studio0);
        studio.insert (studio1);
        studio.insert (studio2);
        studio.print ();

        out.println ();
        IndexTable t_project = movie.project ("title year");
        t_project.print ();
        out.println ();
        IndexTable t_select = movie.select ("title == 'Star_Wars' & year == 1977");
        t_select.print ();

        out.println ();
        IndexTable t_union = movie.union (cinema);
        t_union.print ();

        out.println ();
        IndexTable t_minus = movie.minus (cinema);
        t_minus.print ();

        out.println ();
        IndexTable t_join =  movie.join ("studioName == name", studio);
        t_join.print ();
    } // main

} // MovieDB class

