package cs455.Q5;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Alec on 4/10/2017.
 * Reducer for Q1
 */
public class Q5Reducer extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> values,
					   Context context
	) throws IOException, InterruptedException {
		int[] houseCounts = new int[20];

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
				range = "Less than $15,000";
				break;
			case 2:
				range = "$15,000 - $19,999";
				break;
			case 3:
				range = "$20,000 - $24,999";
				break;
			case 4:
				range = "$25,000 - $29,999";
				break;
			case 5:
				range = "$30,000 - $34,999";
				break;
			case 6:
				range = "$35,000 - $39,999";
				break;
			case 7:
				range = "$40,000 - $44,999";
				break;
			case 8:
				range = "$45,000 - $49,999";
				break;
			case 9:
				range = "$50,000 - $59,999";
				break;
			case 10:
				range = "$60,000 - $74,999";
				break;
			case 11:
				range = "$75,000 - $99,999";
				break;
			case 12:
				range = "$100,000 - $124,999";
				break;
			case 13:
				range = "$125,000 - $149,999";
				break;
			case 14:
				range = "$150,000 - $174,999";
				break;
			case 15:
				range = "$175,000 - $199,999";
				break;
			case 16:
				range = "$200,000 - $249,999";
				break;
			case 17:
				range = "$250,000 - $299,999";
				break;
			case 18:
				range = "$300,000 - $399,999";
				break;
			case 19:
				range = "$400,000 - $499,999";
				break;
			case 20:
				range = "$500,000 or more";
				break;
		}

		//System.out.println(range);

		context.write(new Text(key.toString() + ":"), new Text(range));

	}
}
