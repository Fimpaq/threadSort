package threadSort;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Work {

	private int amount;
	final String file;
	protected static List<Integer> list;
	private long start;
	private long time;
	
	public Work(final int amount, final String file) {
		this.amount = amount;
		this.file = file;
		StatusPanel.pb.setMaximum(amount * 3);
		StatusPanel.pb.setValue(0);

		Runnable runn = new Runnable() {

			@Override
			public void run() {
				try {					
					start = System.currentTimeMillis();
					StatusPanel.setStatus(makeList());
					Thread.sleep(50);
					StatusPanel.setStatus(writeFile());
					Thread.sleep(50);
					StatusPanel.setStatus(createOutput());
				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		};
		ThreadSortMain.executor.execute(runn);
	}
	
	public Work(final String file) {
		this.file = file;

		Runnable runn = new Runnable() {
			@Override
			public void run() {
				try {
					start = System.currentTimeMillis();
					StatusPanel.setStatus(readFile());
					Thread.sleep(50);
					StatusPanel.setStatus(createOutput());
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		ThreadSortMain.executor.execute(runn);
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
	
	private String readFile() {
		StatusPanel.setStatus("reading File ...");

		try {
			Path p = Paths.get(file);

			List<String> data = Files.readAllLines(p);
			this.amount = data.size();
			StatusPanel.pb.setMaximum(this.amount * 2);

			list = new ArrayList<>();

			for (int i = 0; i < amount; i++) {
				list.add(Integer.parseInt(data.get(i)));
				StatusPanel.pb.setValue(i);
				time = System.currentTimeMillis() - start;
				StatusPanel.setTime(timeToReadableString(time));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "just read " + this.amount + " Lines";
	}
	
	private String createOutput() {	

		StatusPanel.setStatus("creating OutputList ...");

		CanvasComponent.listModel.addAll(list); // forEach produziert fehler
		
		StatusPanel.pb.setValue((1 + 3*amount));
//		StatusPanel.pb.setString("done");
		return "OutputList done!";
	}
}
