import java.util.Calendar;

public class WeE {

	public static void main(String[] args) {/*
		Week today = null;
		
		Calendar cal=Calendar.getInstance();
		int week = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(week);
		
		switch(week) {
		case 1:
			today=Week.SUNDAY;
			break;
		case 2:
			today=Week.MONDAY;
			break;
		case 3:
			today=Week.TUSEDAY;
			break;
		case 4:
			today=Week.WEDNESDAY;
			break;
		case 5:
			today=Week.THURSDAY;
			break;
		case 6:
			today=Week.FRAIDAY;
			break;
		case 7:
			today=Week.SATURDAY;
			break;
		}
		System.out.println("오늘 요일: "+today);
		if(today == Week.SUNDAY) {
			System.out.println("일요일에는 축구를 합니다.");
		}else {
			System.out.println("java 공부를 합니다.");
		}
*/
		Week today = Week.SUNDAY;
		String name=today.name();
		System.out.println(name);
		
		int ordinal= today.ordinal();
		System.out.println(ordinal);
		
		Week day1=Week.MONDAY; //0
		Week day2=Week.WEDNESDAY; //2
		int result1=day1.compareTo(day2);
		int result2=day2.compareTo(day1);
		System.out.println(result1);
		System.out.println(result2);
		
		Week weekDay=Week.valueOf("SUNDAY");
		if(weekDay==Week.SATURDAY ||weekDay == Week.SUNDAY ) {
			System.out.println("주말이군요");
		}else {
			System.out.println("평일 이군요");
		}
		Week[] days=Week.values();
		for(Week day : days) {
		System.out.println(day);
		}	
		}

}
