package dive;

import java.util.ArrayList;
import java.util.List;

public class DiveMockedData {

    private List<Dive> dives;

    private static DiveMockedData ourInstance = null;

    public static DiveMockedData getInstance() {
        if(ourInstance == null){
            ourInstance = new DiveMockedData();
        }
        return ourInstance;
    }

    private DiveMockedData() {
        dives = new ArrayList<Dive>();
        dives.add(new Dive("Luke", "40 meters","30 minutes", "0861234567"));

        dives.add(new Dive("Sam", "25 meters","35 minutes", "0861245367"));

        dives.add(new Dive("John", "11 meters","54 minutes", "08626325467"));

        dives.add(new Dive("Paul", "19 meters","24 minutes", "0861274567"));
    }

    public List<Dive> getDives() {
        return dives;
    }

    public Dive getDiveByID(int id){
        for(Dive d: dives){
            if(d.getId() == id){return d;}
        }
        return null;
    }

    public Dive createDive(String name, String depth, String bottomTime, String phone){
        Dive dive = new Dive(name,depth,bottomTime,phone);
        dives.add(dive);
        return dive;
    }

    public boolean delete(int id){
        int diveIndex = -1;
        for(Dive d: dives){
            if(d.getId() == id){
                diveIndex = dives.indexOf(d);
                continue;
            }
        }
        if(diveIndex > -1){
            dives.remove(diveIndex);
        }
        return true;
    }


}
