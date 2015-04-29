package com.github.pozo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class App {
	public static void main(String[] args) {
		ArrayList<Module> modules = createStructure();

		HashMap<CompareKey, Integer> hashMap = new HashMap<CompareKey, Integer>();
		HashSet<Module> columns = new HashSet<Module>();
		HashSet<Instrument> rows = new HashSet<Instrument>();
		
		for (Module module : modules) {
			columns.add(module);
			
			for (Position position : module.getPositions()) {
				rows.add(position.getInstrument());
				CompareKey compareKey = new CompareKey(module, position.getInstrument());
				Integer value = hashMap.get(compareKey);

				if(value == null) {
					value = Integer.valueOf(0);					
				}
				hashMap.put(compareKey, value + position.getInitialWeight());
			}
		}

		for (Module column : columns) {
			for (Instrument row : rows) {
				System.out.print(column.getName() + row.getName());
				System.out.print(" + ");
				System.out.println(hashMap.get(new CompareKey(column,row)));
			}
		}
	}

	private static ArrayList<Module> createStructure() {
		Instrument instrumenta = new Instrument("A");
		Instrument instrumentb = new Instrument("B");
		Instrument instrumentc = new Instrument("C");
		Instrument instrumentd = new Instrument("D");
		
		Module modulea = new Module("A");
		
		Position positiona = new Position(instrumenta);
		Position positionb = new Position(instrumentb);
		Position positionb1 = new Position(instrumentb);
		Position positionb2 = new Position(instrumentb);
		Position positionc = new Position(instrumentc);
		
		
		modulea.addPosition(positiona.setInitialWeight(21));
		modulea.addPosition(positionb.setInitialWeight(10));
		modulea.addPosition(positionb1.setInitialWeight(22));
		modulea.addPosition(positionc.setInitialWeight(33));
		modulea.addPosition(positionb2.setInitialWeight(9));
		
		Module moduleb = new Module("B");
		
		Position positionaB = new Position(instrumenta);
		Position positiondB = new Position(instrumentd);
		Position positioncB = new Position(instrumentc);
		
		moduleb.addPosition(positionaB.setInitialWeight(10));
		moduleb.addPosition(positiondB.setInitialWeight(55));
		moduleb.addPosition(positioncB.setInitialWeight(11));
		
		Module modulec = new Module("C");
		
		Position positionaC = new Position(instrumenta);
		Position positionbC = new Position(instrumentb);
		Position positiondC = new Position(instrumentd);
		Position positiond1C = new Position(instrumentd);
		
		modulec.addPosition(positionaC.setInitialWeight(33));
		modulec.addPosition(positionbC.setInitialWeight(9));
		modulec.addPosition(positiondC.setInitialWeight(11));
		modulec.addPosition(positiond1C.setInitialWeight(16));
		
		ArrayList<Module> modules = new ArrayList<Module>();
		modules.add(modulea);
		modules.add(moduleb);
		modules.add(modulec);
		return modules;
	}
}
