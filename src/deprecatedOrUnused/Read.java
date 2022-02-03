package deprecatedOrUnused;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import threadSort.StatusPanel;

public class Read {

	public int amount;
	final String file;
//	private List<Integer> list;
	long start;
	long time;

	public Read(final String file) {
		this.file = file;

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					StatusPanel.pb.setValue(0);
					start = System.currentTimeMillis();
					StatusPanel.setStatus(readFile());
					Thread.sleep(50);
					StatusPanel.setStatus(createOutput());
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	private String timeToReadableString(final long time) {
		if (time > 1000) {
			return (double) time / 1000 + "s";
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

			Generate.list = new ArrayList<>();

			for (int i = 0; i < amount; i++) {
				Generate.list.add(Integer.parseInt(data.get(i)));
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

		CanvasComponent.listModel.addAll(Generate.list); // anders führt zu arrayindexoutofboundsexception
//		for(int i = 0; i < list.size()-10; i++) {
//			CanvasComponent.listModel.addElement(list.get(i));
//			StatusPanel.pb.setValue((1 + 2*amount) + i);
//			time = System.currentTimeMillis() - start;
//			StatusPanel.setTime(timeToReadableString(time));
//		}	

		StatusPanel.pb.setValue((1 + 3 * amount));
		StatusPanel.pb.setString("done");
		return "OutputList done!";
	}
	
	
}
