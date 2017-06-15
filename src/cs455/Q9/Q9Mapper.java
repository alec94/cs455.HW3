package cs455.Q9;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * get number of people per room on a per state biases
 */
public class Q9Mapper extends Mapper<Object, Text, Text, Text> {
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		int summaryLevel = Integer.parseInt(value.toString().substring(10, 13));
		int segment = Integer.parseInt(value.toString().substring(24, 28));

		if (summaryLevel == 100) {
			String state = value.toString().substring(8, 10);
			if (segment == 1) {
				int totalPop = Integer.parseInt(value.toString().substring(300, 308));
				context.write(new Text(state), new Text("P-" + totalPop));
			} else {
				String rooms = value.toString().substring(2388, 2469);
				int roomTotal = 0;
				int roomCount = 1;
				int index = 0;

				while (index < rooms.length()) {
					int houseCount = Integer.parseInt(rooms.substring(index, Math.min(index + 9, rooms.length()))) * roomCount;
					roomTotal += houseCount * roomCount;
					index += 9;
					roomCount++;
				}

				context.write(new Text(state), new Text("R-" + roomTotal));
			}

		}
	}
}
