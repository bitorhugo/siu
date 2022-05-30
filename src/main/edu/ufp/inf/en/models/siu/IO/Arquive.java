package main.edu.ufp.inf.en.models.siu.IO;


import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Out;
import main.edu.ufp.inf.en.models.siu.database.DataBase;
import main.edu.ufp.inf.en.models.siu.database.node.Node;
import main.edu.ufp.inf.en.models.siu.database.poi.Poi;
import main.edu.ufp.inf.en.models.siu.database.tag.Tag;
import main.edu.ufp.inf.en.models.siu.database.way.Way;
import main.edu.ufp.inf.en.models.siu.user.User;

/**
 * Static class for arquiving purposes
 */
public class Arquive {

    // private constructor
    private Arquive() {}

    private static final String NEWLINE = "\n";
    
    private static final String path_users = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/users/users_out.txt";
    private static final String path_searchedUsers = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/users/searched_users_out.txt";
    private static final String path_datasetUsers = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/users/dataset_users_out.txt";

    private static final String path_nodes = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/nodes/nodes_out.txt";
    private static final String path_searchedNodes = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/nodes/searched_nodes_out.txt";
    private static final String path_datasetNodes = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/nodes/dataset_nodes_out.txt";
    
    private static final String path_ways = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/ways/ways_out.txt";
    private static final String path_searchedWays = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/searched-ways_out.txt";
    private static final String path_datasetWays = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/ways/dataset_ways_out.txt";

    private static final String path_poi = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/pois/poi_out.txt";
    private static final String path_searchedPoi = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/pois/searched_pois_out.txt";
    private static final String path_datasetPois = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/pois/dataset_pois_out.txt";

    private static final String path_tags = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/tags_out.txt";
    private static final String path_searchedTags = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/searched_tags_out.txt";

    private static final String path_graph = "/Users/VitorHugo/dev/java/projects/siu/data/out/txt/maps/graph_out.txt";

    public static void User (User u) {
        Out out = new Out(path_users);
        try {
            out.print(u);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void Users (DataBase db) {
        Out out = new Out(path_datasetUsers);
        out.print(db.numberOfUsers() + NEWLINE);
        try {
            for (var userId : db.usersKeys()) {
                User u = db.searchUser(userId);
                out.print(u + NEWLINE);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void searchedUser (User u) {
        Out out = new Out(path_searchedUsers);
        try {
            out.print(u);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void Node (Node n) {
        Out out = new Out(path_nodes);
        try {
            out.print(n);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void Nodes (DataBase db) {
        Out out = new Out(path_datasetNodes);
        try {
            out.print(db.numberOfNodes() + NEWLINE);
            for (var v : db.nodesKeys()) {
                Node n = db.searchNode(v);
                out.print(n + NEWLINE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public static void searchedNode (Node n) {
        Out out = new Out(path_searchedNodes);
        try {
            out.print(n);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void Poi(Poi p) {
        Out out = new Out(path_poi);
        try {
            out.print(p);        
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void Pois (DataBase db) {
        Out out = new Out(path_datasetPois);
        try {
            out.print(db.numberOfPois() + NEWLINE);
            for (var v : db.poisKeys()) {
                Poi u = db.searchPoi(v);
                out.print(u + NEWLINE);
            }    
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public static void searchedPoi(Poi p) {
        Out out = new Out(path_searchedPoi);
        try {
            out.print(p);        
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void Way(Way w) {
        Out out = new Out(path_ways);
        try {
            out.print(w);
        } catch (Exception e) {
        e.getMessage();
        } finally {
            out.close();
        }
    }
    
    public static void Ways (DataBase db) {
        Out out = new Out(path_datasetWays);
        try {
            out.print(db.numberOfWays() + NEWLINE);
            for (var v : db.waysKeys()) {
                Way u = db.searchWay(v);
                out.print(u + NEWLINE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public static void searchedWay(Way w) {
        Out out = new Out(path_searchedWays);
        try {
            out.print(w);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void Tag(Tag t) {
        Out out = new Out(path_tags);
        try {
            out.print(t);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void searchedTag(Tag t) {
        Out out = new Out(path_searchedTags);
        try {
            out.print(t);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void Tag(Node n, Tag t) {
        Out out = new Out(path_tags);
        try {
            out.print(t);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void searchedTag(Node n, Tag t) {
        Out out = new Out(path_searchedTags);
        try {
            out.print(t);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void Tag(Way w, Tag t) {
        Out out = new Out(path_tags);
        try {
            out.print(w);
            out.print(t);
            out.println();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void searchedTag(Way w, Tag t) {
        Out out = new Out(path_searchedTags);
        try {
            out.print(w);
            out.print(t);
            out.println();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }

    public static void graph (EdgeWeightedDigraph graph) {
        Out out = new Out(path_graph);
        try {
            out.println(graph.V()); // vertices
            out.println(graph.E()); // edges
            for (int i = 0; i < graph.V(); i++) {
                for (var edge : graph.adj(i)) {
                    out.println(edge.from() + " " + edge.to() + " " + edge.weight());
                }
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            out.close();
        }
    }
}
