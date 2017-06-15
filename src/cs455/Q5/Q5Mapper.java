package cs455.Q5;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Mapper for Q1
 */
public class Q5Mapper extends Mapper<Object, Text, Text, Text> {
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		int summaryLevel = Integer.parseInt(value.toString().substring(10, 13));
		int segment = Integer.parseInt(value.toString().substring(24, 28));

		if (summaryLevel == 100 && segment == 2) {
			String state = value.toString().substring(8, 10);
			String houseValues = value.toString().substring(2928, 3108);
			String result = "";

			int index = 0;

			while (index < houseValues.length()) {
				result += Integer.parseInt(houseValues.substring(index, Math.min(index + 9, houseValues.length()))) + "-";
				index += 9;
			}

			context.write(new Text(state), new Text(result));
		}
	}
}
