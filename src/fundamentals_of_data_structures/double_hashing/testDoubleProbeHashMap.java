package fundamentals_of_data_structures.double_hashing;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests DoubleProbeHashMap
 *
 * @author jameselder
 */
public class testDoubleProbeHashMap {
//	public static List<String> trace = new ArrayList<String>();
	public static void main(String[] args) throws Exception {
		AsciiReader marathonReader;
		Object[] Format = { Integer.class, String.class, String.class, String.class, String.class, String.class };
		Object[] result;
		
		ProbeHashMap<String, MarathonRunner> marathonMap = new ProbeHashMap<>(7500);
		DoubleProbeHashMap<String, MarathonRunner> marathonMap2 = new DoubleProbeHashMap<>(7500);
		MarathonRunner marathonRunner;

		try {
			marathonReader = new AsciiReader("D:\\Jacob\\Study\\EECS\\EECS2011\\Assignment 3\\marathon2017.csv");
			do {
				result = marathonReader.ReadLine(Format, ",");
				if (result != null) {
					marathonRunner = new MarathonRunner((Integer) result[0], (String) result[1], (String) result[2],
							(String) result[3], (String) result[4], (String) result[5]);
//					 System.out.println(marathonRunner.toString());
					try { // use name and category as key
						marathonRunner = marathonMap.put(
								(String) result[3] + " " + (String) result[4] + " " + (String) result[5],
								marathonRunner);
						if (marathonRunner != null) {
							System.out.println(marathonRunner.toString()); // print duplicates
						}
					} catch (Exception ex) {
						System.out.println("Incorrect: ProbeHashMap.put threw an exception.");
					}
				}
			} while (result != null);
		} catch (Exception x) {
			System.out.println("File not found");
		}

		try {
			marathonRunner = marathonMap.get("Cartmell Greg M25-29");
			System.out.println(marathonRunner.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Incorrect: DoubleProbeHashMap.get threw an exception.");
		}
		System.out.println("Total Probes: " + marathonMap.getTotalProbes());

		try {
			marathonRunner = marathonMap.get("Weigner Brent M65-69");
			System.out.println(marathonRunner.toString());
		} catch (Exception ex) {
			System.out.println("Incorrect: DoubleProbeHashMap.get threw an exception.");
		}
		System.out.println("Total Probes: " + marathonMap.getTotalProbes());

		try {
			marathonRunner = marathonMap.get("Peller Jeff M50-54");
			System.out.println(marathonRunner.toString());
		} catch (Exception ex) {
			System.out.println("Incorrect: DoubleProbeHashMap.get threw an exception.");
		}
		System.out.println("Total Probes: " + marathonMap.getTotalProbes());

		try {
			marathonRunner = marathonMap.get("Reid Leonet M25-29");
			System.out.println(marathonRunner.toString());
		} catch (Exception ex) {
			System.out.println("Incorrect: DoubleProbeHashMap.get threw an exception.");
		}
		System.out.println("Total Probes: " + marathonMap.getTotalProbes());

		System.out.println("----------------------------------------------------------");
		try {
			// marathonReader = new AsciiReader("marathon.csv");
			marathonReader = new AsciiReader("D:\\Jacob\\Study\\EECS\\EECS2011\\Assignment 3\\marathon2017.csv");
			do {
				result = marathonReader.ReadLine(Format, ",");
				if (result != null) {
					marathonRunner = new MarathonRunner((Integer) result[0], (String) result[1], (String) result[2],
							(String) result[3], (String) result[4], (String) result[5]);
//					 System.out.println(marathonRunner.toString());
					try { // use name and category as key
						marathonRunner = marathonMap2.put(
								(String) result[3] + " " + (String) result[4] + " " + (String) result[5],
								marathonRunner);
						if (marathonRunner != null) {
							System.out.println(marathonRunner.toString()); // print duplicates
						}
					} catch (Exception ex) {
						System.out.println("Incorrect: ProbeHashMap.put threw an exception.");
					}
				}
			} while (result != null);
		} catch (Exception x) {
			System.out.println("File not found");
		}
		
		try {
			marathonRunner = marathonMap2.get("Cartmell Greg M25-29");
			System.out.println(marathonRunner.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Incorrect: DoubleProbeHashMap.get threw an exception.");
		}
		System.out.println("Total Probes: " + marathonMap2.getTotalProbes());

		try {
			marathonRunner = marathonMap2.get("Weigner Brent M65-69");
			System.out.println(marathonRunner.toString());
		} catch (Exception ex) {
			System.out.println("Incorrect: DoubleProbeHashMap.get threw an exception.");
		}
		System.out.println("Total Probes: " + marathonMap2.getTotalProbes());

		try {
			marathonRunner = marathonMap2.get("Peller Jeff M50-54");
			System.out.println(marathonRunner.toString());
		} catch (Exception ex) {
			System.out.println("Incorrect: DoubleProbeHashMap.get threw an exception.");
		}
		System.out.println("Total Probes: " + marathonMap2.getTotalProbes());

		try {
			marathonRunner = marathonMap2.get("Wherry Michael M50-54");
			System.out.println(marathonRunner.toString());
		} catch (Exception ex) {
			System.out.println("Incorrect: DoubleProbeHashMap.get threw an exception.");
		}
		System.out.println("Total Probes: " + marathonMap2.getTotalProbes());
		
		
		
		System.out.println("-------------------------------------------");
		/*try {
			String FileName = "D:\\Jacob\\Study\\EECS\\EECS2011\\Assignment 3\\a.txt";
			FileOutputStream fos = new FileOutputStream(FileName);
			for (int i = 0; i < marathonMap2.table.length; i++) {
				if (marathonMap2.table[i] != null) {
					String str = i + " : " + marathonMap2.table[i].toString();
					byte [] data = str.getBytes();
					fos.write(data);
					fos.write('\n');
				}
			}
			fos.flush();
			fos.close();
			System.out.println("Done");
			
		} catch (Exception e) {
			System.out.println("exception");
			e.printStackTrace();
		}
		
		try {
        	String FileName = "D:\\Jacob\\Study\\EECS\\EECS2011\\Assignment 3\\b.txt";
        	FileOutputStream fos = new FileOutputStream(FileName, true);
        	for (int i = 0; i < trace.size(); i++) {
        		String str = trace.get(i);
        		byte [] data = str.getBytes();
        		fos.write(data);
        		fos.write('\n');        		
        	}
        	fos.flush();
        	fos.close();
        } catch (Exception e) {
        	System.out.println("exception");
        	e.printStackTrace();
        }
		
		System.out.println(marathonMap.table.length);
		System.out.println(marathonMap2.table.length);
		int ce = 0;
		for (int i = 0; i < marathonMap.table.length; i++) {
			if (marathonMap.table[i] != null)
				ce++;
		}
		System.out.println(ce);
		int ce2 = 0;
		for (int i = 0; i < marathonMap2.table.length; i++) {
			if (marathonMap2.table[i] != null)
				ce2++;
		}
		System.out.println(ce2);
		System.out.println(marathonMap.table[0] == marathonMap2.table[0]);*/
	}
}
