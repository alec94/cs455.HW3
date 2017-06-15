package cs455.Q6;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alec on 4/10/2017.
 * Reducer for Q1
 */
public class Q6Reducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		int[] houseCounts = new int[17];

		for (int i = 0; i < houseCounts.length; i++) {
			houseCounts[i] = 0;
		}

		for (Text value : values) {
			String[] houseValues = value.toString().split("-");

			for (int i = 0; i < houseValues.length; i++) {
				houseCounts[i] += Integer.parseInt(houseValues[i]);
			}
		}

		ArrayList<Integer> houses = new ArrayList<>(); // holds number of price range identifiers equal to the number of houses in that range
		int h = 1; //price range identifier
		for (int i : houseCounts) {
			for (int j = 0; j < i; j++) {
				houses.add(h);
			}
			h++;
		}

		Collections.sort(houses);

		//System.out.println(Arrays.toString(houses.toArray()));
		int houseID = houses.get(houses.size() / 2);

		if (houseID == 0) {
			houseID = 1;
		}

		String range = "";

		switch (houseID) {

			case 1:
				range = "Less than $100";
				break;
			case 2:
				range = "$100 - $149";
				break;
			case 3:
				range = "$150 - $199";
				break;
			case 4:
				range = "$200 - $246";
				break;
			case 5:
				range = "$250 - $299";
				break;
			case 6:
				range = "$300 - $349";
				break;
			case 7:
				range = "$350 - $399";
				break;
			case 8:
				range = "$400 - $449";
				break;
			case 9:
				range = "$500 - $549";
				break;
			case 10:
				range = "$550 - $599";
				break;
			case 11:
				range = "$600 - $649";
				break;
			case 12:
				range = "$650 - $699";
				break;
			case 13:
				range = "$700 - $749";
				break;
			case 14:
				range = "$750- $999";
				break;
			case 15:
				range = "$1000 or more";
				break;
			case 16:
				range = "No cash rent";
				break;
		}

		//System.out.println(range);

		context.write(new Text(key.toString() + ":"), new Text(range));
	}
}
