package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.HashMap;

public class SessionManager {
    private HashMap<Integer, UserSession> sessions;
    private int sessionValid;

    public SessionManager(int sessionValid) {
        this.sessionValid = sessionValid;
        sessions = new HashMap<>();
    }

    public void add(UserSession userSession) {
        sessions.put(userSession.getSessionHandle(), userSession);
    }

    public UserSession find(String userName) {
        int handle = 0;
        UserSession userSes;
        for (var ses : sessions.entrySet()) {
            userSes = ses.getValue();
            if (userName.equals(userSes.getUserName())) {
                handle = ses.getKey();
                LocalDateTime ldt = userSes.getLastAccess();
                ldt = ldt.plusSeconds(sessionValid);
                if (ldt.isBefore(LocalDateTime.now())) return null;
                else break;
            }
        }
        if (handle != 0) {
            userSes = sessions.get(handle);
            userSes.updateLastAccess();
            sessions.put(handle, userSes);
            return userSes;
        }
        return null;
    }

    public UserSession get(int sessionHandle) {
        UserSession userSes = sessions.get(sessionHandle);
        if (userSes != null) {
            LocalDateTime ldt = userSes.getLastAccess();
            ldt = ldt.plusSeconds(sessionValid);
            if (ldt.isBefore(LocalDateTime.now())) return null;
            else {
                userSes.updateLastAccess();
                sessions.put(sessionHandle, userSes);
            }
        }
        return userSes;
    }

    public void delete(int sessionHandle) {
        sessions.remove(sessionHandle);
    }

    public void deleteExpired() {
        ArrayDeque<Integer> adi = new ArrayDeque<>();
        UserSession userSes;
        for (var ses : sessions.entrySet()) {
            userSes = ses.getValue();
            LocalDateTime ldt = userSes.getLastAccess();
            ldt = ldt.plusSeconds(sessionValid);
            if (ldt.isBefore(LocalDateTime.now())) {
                adi.offer(ses.getKey());
            }
        }
        for (Integer key : adi) {
            sessions.remove(key);
        }
    }
}
