package agile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrderSiblingsByAge {
	public HashMap<Object, Object> hm = new HashMap<Object, Object>();
	public HashMap<Object, String> hp = new HashMap<Object, String>();
	public parser var= new parser();
	
	public void orderByAge(parser par){
		System.out.println("FAMILY: US28: Order Siblings by age");
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("                             Siblings by Age                                ");
		System.out.println("----------------------------------------------------------------------------");
		System.out.printf("|%-10s|%-64s", "Family ID", "Siblings");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
		for (Individual induvidual : par.individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getAge(), induvidual);
			hm.put(induvidual.getBirthDate(), induvidual);
		}
		for (Family famy : par.families_list) {
			String family_id =famy.getId();
			String child_id= famy.getChildId();
			System.out.printf("|%-10s|",family_id);
			String children[]=child_id.split("\\s+");
			//split child id's at spaces
			for(String child: children){
				Individual c=(Individual) hm.get(child);
				if(c!=null){
					hp.put(c.getAge(), child);
				}}
		if(hp!=null){
		SortedSet<Object> keys = new TreeSet<Object>(hp.keySet());
		for(Object ss: keys){
			//System.out.printf("|%-15s|%-35s|", "Family ID", "Siblings");
			System.out.printf("%-5s",hp.get(ss));
		}		}
System.out.println();
hp.clear();
			}
		System.out.println("----------------------------------------------------------------------------");
	}
	}