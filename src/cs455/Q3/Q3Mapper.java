package cs455.Q3;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Alec on 4/10/2017.
 * Mapper for Q1
 */
public class Q3Mapper extends Mapper<Object, Text, Text, Text> {
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		int summaryLevel = Integer.parseInt(value.toString().substring(10, 13));
		int segment = Integer.parseInt(value.toString().substring(24, 28));

		if (summaryLevel == 100 && segment == 1) {
			String state = value.toString().substring(8, 10);
			String male18String = value.toString().substring(3864, 3981); //117
			String male29String = value.toString().substring(3981, 4026); //45
			String male39String = value.toString().substring(4026, 4044); //18
			String maleOtherString = value.toString().substring(4044, 4143); //99
			String female18String = value.toString().substring(4143, 4260);
			String female29String = value.toString().substring(4260, 4305);
			String female39String = value.toString().substring(4305, 4323);
			String femaleOtherString = value.toString().substring(4323, 4422);

			int male18 = 0;
			int male29 = 0;
			int male39 = 0;
			int maleTotal = 0;
			int female18 = 0;
			int female29 = 0;
			int female39 = 0;
			int femaleTotal = 0;

			int index = 0;

			while (index < male18String.length()) {
				male18 += Integer.parseInt(male18String.substring(index, Math.min(index + 9, male18String.length())));
				index += 9;
			}

			index = 0;
			while (index < male29String.length()) {
				male29 += Integer.parseInt(male29String.substring(index, Math.min(index + 9, male29String.length())));
				index += 9;
			}

			index = 0;
			while (index < male39String.length()) {
				male39 += Integer.parseInt(male39String.substring(index, Math.min(index + 9, male39String.length())));
				index += 9;
			}

			index = 0;
			while (index < maleOtherString.length()) {
				maleTotal += Integer.parseInt(maleOtherString.substring(index, Math.min(index + 9, maleOtherString.length())));
				index += 9;
			}

			//Female
			index = 0;
			while (index < female18String.length()) {
				female18 += Integer.parseInt(female18String.substring(index, Math.min(index + 9, female18String.length())));
				index += 9;
			}

			index = 0;
			while (index < female29String.length()) {
				female29 += Integer.parseInt(female29String.substring(index, Math.min(index + 9, female29String.length())));
				index += 9;
			}

			index = 0;
			while (index < female39String.length()) {
				female39 += Integer.parseInt(female39String.substring(index, Math.min(index + 9, female39String.length())));
				index += 9;
			}

			index = 0;
			while (index < femaleOtherString.length()) {
				femaleTotal += Integer.parseInt(femaleOtherString.substring(index, Math.min(index + 9, femaleOtherString.length())));
				index += 9;
			}


			maleTotal += male18;
			maleTotal += male29;
			maleTotal += male39;
			femaleTotal += female18;
			femaleTotal += female29;
			femaleTotal += female39;

			String result = male18 + "-" + male29 + "-" + male39 + "-" + maleTotal + "-" + female18 + "-" + female29 + "-" + female39 + "-" + femaleTotal;

			//System.out.println(result);
			context.write(new Text(state), new Text(result));
		}
	}
}
