package agile;
import java.io.*;
import java.util.*;

public class parser {
	//storing list of individuals and families
    public List<Individual> individuals_list = new ArrayList<Individual>();
    public List<Family> families_list = new ArrayList<Family>();
    
    private String getVar(String[] parseLine) {
        String var = "";
        for (int i = 2; i < parseLine.length; i++) {
            var = var + " " + parseLine[i];
        }
        return var.trim();
    }
    //replacing "@" by space in id
    private String getId(String Id) {
        return Id.replace("@", "");
    }
    //read and parse file
    public void readFile(String file) throws IOException {

        FileInputStream fileInput = null;
        BufferedReader bufferRead = null;

        try {

            fileInput = new FileInputStream(file);
            bufferRead = new BufferedReader(new InputStreamReader(fileInput));
            
            String line = null;
            boolean flagIndi = false;
            boolean flagBirthDate = true;
            Individual ind = null;
            Family fam = null;

            while ((line = bufferRead.readLine()) != null) {
                String[] parseLine = (line.split("\\s+"));
                int level = Integer.valueOf(parseLine[0]);
                String tag = parseLine[1];
                String var = (parseLine.length > 2) ? getVar(parseLine) : null;
                //check for level 0 tags
                if (level == 0) {
                    if ("INDI".equals(var)) {
                        ind = new Individual();
                        if (ind != null) {
                            ind.setId(getId(tag));
                            individuals_list.add(ind);
                            flagIndi = true;
                        }
                    } else if ("FAM".equals(var)) {
                        fam = new Family();
                        if (fam != null) {
                            families_list.add(fam);
                            fam.setId(getId(tag));
                            flagIndi = false;
                        }
                    } else {
                        flagIndi = false;
                    }
                }
                //check for level 1 tags
                if (level == 1) {
                    if (flagIndi) {
                        if (tag.equals("NAME")) {
                            ind.setName(var);
                           // System.out.println(var+"\n");
                        }
                        if (tag.equals("SEX")) {
                            ind.setSex(var.charAt(0));
                        }
                        if (tag.equals("BIRT")) {
                            flagBirthDate = true;
                        }
                       if (tag.equals("DEAT")) {
                           flagBirthDate = false;
                        }
                    } else {
                        if ("HUSB".equals(tag)) {
                            fam.setHusbandId(getId(var));
                        } else if ("WIFE".equals(tag)) {
                            fam.setWifeId(getId(var));
                        } 
                        
                        if ("MARR".equals(tag)) {
                            line = bufferRead.readLine();
                            String[] nextLine = (line.split("\\s+"));
                            if (nextLine[1].equals("DATE")) {
                                String wedDate = nextLine[2] + " " + nextLine[3] + " " + nextLine[4];
                                fam.setWeddingDate(wedDate);
                            }
                        } 
                    }
                }
                //check for level 2 tags
                if (level == 2) {
                    if (flagIndi) {
                        if (tag.equals("SURN")) {
                            ind.setSurName(var);
                        }
                        if (tag.equals("GIVN")) {
                            ind.setGivenName(var);
                        }
                        if (tag.equals("DATE")) {
                            if (flagBirthDate) ind.setBirthDate(var);
                            else ind.setDeathDate(var);
                        }
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File is not found!");
        }

        setIndividualsInFamilies();
    }
  //return individuals details
   public Individual getIndividual(String id) {
        if (individuals_list != null && !individuals_list.isEmpty()) {
            for (int i = 0; i < individuals_list.size(); i++) {
                Individual individualObject = individuals_list.get(i);
                if (individualObject.getId().equals(id)) {
                    return individualObject;
                }
            }
        }
        return null;
    }
  //set family member's details
    private void setIndividualsInFamilies() {
        for (int i = 0; i < families_list.size(); i++) {
            Family fam = families_list.get(i);
            if (fam.getHusbandId() != null) {
                fam.setHusband(getIndividual(fam.getHusbandId()));
            }
            if (fam.getWifeId() != null) {
                fam.setWife(getIndividual(fam.getWifeId()));
            }
        }
    }

}
