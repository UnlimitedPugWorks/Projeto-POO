package sth.core;
import java.util.ArrayList;

interface Observer{
	void update(Notification n);
	ArrayList<String> readInbox();
}