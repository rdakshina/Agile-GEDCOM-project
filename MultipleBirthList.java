package agile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultipleBirthList {
	public HashMap<String, Object> hm = new HashMap<String, Object>();
	public parser var= new parser();
	
	void CheckBirths(parser par){
		System.out.println("FAMILY: US32: Displaying Multiple Births");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("                            Multiple Births List                          ");
		System.out.println("--------------------------------------------------------------------------");
		System.out.printf("|%-15s|%-15s|%-15s|%-25s|", "Family ID", "Birthday", " Individual ID", "Name");
		System.out.println();
		System.out.println("--------------------------------------------------------------------------");
		for (Individual induvidual : par.individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getBirthDate(), induvidual);
			hm.put(induvidual.getName(), induvidual);
		}

		for (Family fam : par.families_list) {
			String child_id=fam.getChildId();
			String children[]=child_id.split("\\s+");
			//split child id's at spaces
			
			for(String temp: children){
				Individual c=(Individual) hm.get(temp);
				if(c!=null){
					String Bdate=null;
			        LocalDate locBDate=null;
					Bdate=c.getBirthDate();
			        if(!"null".equals(Bdate))
				        {
				        	
				        	locBDate = var.convertToLocalDate(Bdate);
				        }
			        for(String temp1: children){
			        	Individual c2=(Individual) hm.get(temp1);
						if(c2!=null){
			        	String B2date=null;
				        LocalDate locB2Date=null;
						B2date=c2.getBirthDate();
				        if(!"null".equals(B2date))
					        {
					        	
					        	locB2Date = var.convertToLocalDate(B2date);
					
					        }
				        
			        	if(locB2Date.equals(locBDate)){
			        		
			        		if(!temp.equals(temp1)){
			        			System.out.printf("|%-15s|%-15s|%-15s|%-25s|", fam.getId(), Bdate, temp, hm.get(temp));
				        		System.out.println();
			        		System.out.printf("|%-15s|%-15s|%-15s|%-25s|",  " ", " ", temp1, hm.get(temp1));
			        		System.out.println();
			        		}
			        	}
			        }
				}		       
			        
			        
			}
			
			}
			
		}	System.out.println("--------------------------------------------------------------------------");
	}
	
}