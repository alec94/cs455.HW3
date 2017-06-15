package cs455.Q4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Mapper for Q1
 */
public class Q4Mapper extends Mapper<Object, Text, Text, Text> {
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		int summaryLevel = Integer.parseInt(value.toString().substring(10, 13));
		int segment = Integer.parseInt(value.toString().substring(24, 28));

		int urban = 0;
		int rural = 0;
		int undefined = 0;

		if (summaryLevel == 100 && segment == 2) {
			String state = value.toString().substring(8, 10);
			urban += Integer.parseInt(value.toString().substring(1821, 1830));
			urban += Integer.parseInt(value.toString().substring(1830, 1839));
			rural += Integer.parseInt(value.toString().substring(1839, 1848));
			undefined += Integer.parseInt(value.toString().substring(1848, 1857));


			String result = urban + "-" + rural + "-" + undefined;
			context.write(new Text(state), new Text(result));
		}
	}
}
