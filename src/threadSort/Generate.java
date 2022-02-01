package threadSort;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generate {

	final int amount;
	final String file;
	public static List<Integer> list;
	private long start;
	private long time;

	public Generate(final int amount, final String file) {
		this.amount = amount;
		this.file = file;
		StatusPanel.pb.setMaximum(amount * 3);

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {					
					StatusPanel.pb.setValue(0);
					start = System.currentTimeMillis();
					StatusPanel.setStatus(makeList());
					Thread.sleep(500);
					StatusPanel.setStatus(writeFile());

					StatusPanel.setStatus(createOutput());
				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}).start();	
	}
	
	private String timeToReadableString(final long time) {
		if(time > 1000) {
			return (double)time / 1000 + "s";
		} else {
			return time + "ms";
		}
	}

	private String makeList() {		
		StatusPanel.setStatus("generating List ...");
		
		Random random = new Random();
		list = new ArrayList<>();
		
		for (int i = 0; i < this.amount; i++) {
			list.add(random.nextInt());			
			StatusPanel.pb.setValue(i);
			time = System.currentTimeMillis() - start;
			StatusPanel.setTime(timeToReadableString(time));
		}		
		
		return "generating List done!";
	}

	private String writeFile() {
		StatusPanel.setStatus("writing File ... ");
		
		Path p = Paths.get(this.file + ".txt");
		try {			

			if(!Files.exists(p)) {
				Files.createFile(p);
				System.err.println("File created: " + p.toString());
			}
			
			for (int i = 0; i < amount; i++) {
				Files.write(p, list.get(i).toString().getBytes(), StandardOpenOption.APPEND);
				Files.write(p, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
				StatusPanel.pb.setValue(amount + i);
				time = System.currentTimeMillis() - start;
				StatusPanel.setTime(timeToReadableString(time));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "written to: " + p.toString();
	}
	
	private String createOutput() {	

		StatusPanel.setStatus("creating OutputList ...");

		CanvasComponent.listModel.addAll(list); // anders führt zu arrayindexoutofboundsexception
//		for(int i = 0; i < list.size()-10; i++) {
//			CanvasComponent.listModel.addElement(list.get(i));
//			StatusPanel.pb.setValue((1 + 2*amount) + i);
//			time = System.currentTimeMillis() - start;
//			StatusPanel.setTime(timeToReadableString(time));
//		}	
		
		StatusPanel.pb.setValue((1 + 3*amount));
		StatusPanel.pb.setString("done");
		return "OutputList done!";
	}
	
}

