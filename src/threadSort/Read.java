package threadSort;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Read {
	
	public int amount;
	final String file;
	private List<Integer> list;
	long start;
	long time;
	
	public Read(final String file) {
		this.file = file;
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				start = System.currentTimeMillis();
				StatusPanel.setStatus(readFile());
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

	public String readFile() {
		StatusPanel.setStatus("reading File ...");
		
		try {
			Path p = Paths.get(file);

			List<String> data = Files.readAllLines(p);
			this.amount = data.size();
			StatusPanel.pb.setMaximum(this.amount);
			
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
}
