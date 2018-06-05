package calendar;

public class Calendar {
	public static int yearDays(int year) {
		return ((year - 2000)%4 == 0) ? 366 : 365;
	}
	
	public static int monthDays(int year, int month) {
		int days = 0;
		if (month != 0) {
			switch(month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				break;
			case 2:
				days = (yearDays(year) == 366) ? 29 : 28;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				days = 30;
				break;
			default:
				break;
			}
		}
		
		return days;
	}
	
	public static int durationTo2000_1_1(int year, int month, int day) {
		int duration = day;
		for (int i = 1; i < month; i++) {
			duration += monthDays(year, i);
		}
		
		if (year > 2000) {
			for (int i = 2000; i < year; i++) {
				duration += yearDays(i);
			}
		} else {
			for (int i = 2000; i > year; i--) {
				duration += yearDays(i);
			}
		}
		
		return duration;
	}
	
	public static int week(int year, int month, int day) {
		int week = 0;
		int k =durationTo2000_1_1(year, month, day) % 7;
		if (k > 2) {
			week = k - 2;
		} else {
			week = k + 5;
		}
		
		return week;
	}
	
	public static String toMonthString(int year, int month) {
		String monthString = "\t\t----" + year + "年-" + month +  "月----\n" + "日\t一\t二\t三\t四\t五\t六\n";
		int count = week(year, month, 1);
		if (count == 7) {
			count = 0;
		}
		for (int i = 0; i < count; i++) {
			monthString += "\t";
		}
		for (int i = 1; i <= monthDays(year, month); i++) {
			if (count != 6) {
				monthString += i + "\t";
				count++;
			} else {
				monthString += i + "\n";
				count = 0;
			}
		}
		
		return monthString + "\n";
	}
	
	public static String toYearString(int year) {
		String yearString = "";
		int weekCount = 0;
		
		for (int j = 1; j < 13; j++) {
			String monthString = "\t\t----" + year + "年-" + j +  "月----\n" + "日\t一\t二\t三\t四\t五\t六\t周\n";
			int count = week(year, j, 1);
			int last = week(year, j, monthDays(year, j));
			
			if (count == 7) {
				count = 0;
			}
			for (int i = 0; i < count; i++) {
				monthString += "\t";
			}
			for (int i = 1; i <= monthDays(year, j); i++) {
				if (count != 6) {
					monthString += i + "\t";
					count++;
				} else {
					weekCount++;
					monthString += i + "\t" + weekCount + "\n";
					count = 0;
				}
			}
			if (last != 6) {
				weekCount++;
				int empty = ((7 - last) == 0) ? 7 : (7 - last);
				for (int i = 1; i < empty; i++) {
					monthString += "\t";
				}
				yearString += monthString + weekCount + "\n\n";
				weekCount--;
			} else {
				yearString += monthString + "\n\n";
			}
		}
		return yearString;
	}

	public static void main(String[] args) {

		System.out.println(toYearString(2018));
	}

}
