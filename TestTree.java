import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class TestTree {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String xmlString = pathToXml("C:\\Users\\Moussa\\OneDrive\\Bureau\\dic1");
        Composant racine = xmlToDoc(xmlString);
        racine.afficher(0);

    }

    public static String pathToXml(String vpath) {
        File path = new File(vpath);
        File[] list = path.listFiles();
        String strXml = "<Directory name='" + path.getName() + "'>\n";
        if (path.exists()) {
            for (File file : list) {
                if (file.isDirectory()) {
                    strXml = strXml + pathToXml(file.getPath());
                } else if (file.isFile()) {
                    strXml = strXml + "<File name='" + file.getName() + "'/>\n";
                }
            }
        } else {
            System.out.println("ce chemin n'existe pas");
        }
        strXml = strXml + "</Directory>\n";
        return strXml;
    }

    public static Composant xmlToDoc(String xmlString) throws ParserConfigurationException, SAXException, IOException {
        String ch = "<?xml version='1.0'?>" + xmlString;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(ch);
        ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);

        Element element = doc.getDocumentElement();
        return (insertElement(element));
    }

    public static Composant insertElement(Element element) {
        Dossier racine = new Dossier(element.getAttribute("name"));
        NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elt = (Element) node;
                if (node.getNodeName().equals("File")) {
                    Composant fichier = new Fichier(elt.getAttribute("name"));
                    racine.ajouter(fichier);
                } else if (elt.getNodeName().equals("Directory")) {
                    racine.ajouter(insertElement(elt));
                }
            }
        }
        return racine;
    }
}
