package cs455.Q7;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Mapper for Q7
 * gets average number of rooms per house per state
 */
public class Q7Mapper extends Mapper<Object, Text, Text, Text> {
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		int summaryLevel = Integer.parseInt(value.toString().substring(10, 13));
		int segment = Integer.parseInt(value.toString().substring(24, 28));

		if (summaryLevel == 100 && segment == 2) {
			String state = value.toString().substring(8, 10);
			String rooms = value.toString().substring(2388, 2469);
			int roomCount = 1;
			int roomTotal = 0;
			int houseTotal = 0;

			int index = 0;

			while (index < rooms.length()) {
				int houseCount = Integer.parseInt(rooms.substring(index, Math.min(index + 9, rooms.length()))) * roomCount;
				roomTotal += houseCount * roomCount;
				houseTotal += houseCount;
				index += 9;
				roomCount++;
			}

			context.write(new Text(state), new Text(houseTotal + "-" + roomTotal));

		}
	}
}
