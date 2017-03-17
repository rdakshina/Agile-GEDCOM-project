package agile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BirthBeforeMarriageOfParents {
	
	public HashMap<String, Object> hm = new HashMap<String, Object>();
	
	void checkChildBirth(List<Individual> individuals_list,List<Family> families_list){
		for (Individual induvidual : individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getBirthDate(), induvidual);
		}

		for (Family fam : families_list) {
			String child_id=fam.getChildId();
			String children[]=child_id.split("\\s+");
			//split child id's at spaces
			
			String WeddingDate=fam.getWeddingDate();
			
			for(String temp: children){
				Individual c=(Individual) hm.get(temp);
		
				if (c!= null) {
					SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
			        Date birthdate=null;
			        Date marriageDate=null;
			        String Bdate=c.getBirthDate();
			        try{
			        if(Bdate!=null)
			        birthdate = format.parse(Bdate);
			        } catch (ParseException e) {
			        	e.printStackTrace();
			        }
			        try{
			        marriageDate = format.parse(WeddingDate);
			        }catch (ParseException e){
			        	e.printStackTrace();
			        }
			        if(birthdate!=null){
			        if (marriageDate.compareTo(birthdate) == 1) {
			        	System.out.println("ERROR: US08: FAMILY: CHILD " +temp+" Born before marriage of parents"+"Marriage dat:"+WeddingDate+" Birthdate:"+Bdate);
			            
			        } else {
			        	//System.out.printf("\n Child "+temp+" check completed");
			            
			        }
				}
				  else
			        {
			        	//System.out.printf("\n No children in family:"+fam.getId());
			
			        }
				}
			}
			
			
		
		
	}
		System.out.printf("FAMILY :US08: Birth before marriage of parents check completed");
	}
	
}
