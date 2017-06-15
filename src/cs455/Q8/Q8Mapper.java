package cs455.Q8;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Mapper for Q8
 */
public class Q8Mapper extends Mapper<Object, Text, Text, Text> {
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		int summaryLevel = Integer.parseInt(value.toString().substring(10, 13));
		int segment = Integer.parseInt(value.toString().substring(24, 28));

		if (summaryLevel == 100 && segment == 1) {
			String state = value.toString().substring(8, 10);
			int totalPop = Integer.parseInt(value.toString().substring(300, 308));
			int over85 = Integer.parseInt(value.toString().substring(1065, 1074));

			context.write(new Text(state), new Text(totalPop + "-" + over85));

		}
	}
}
