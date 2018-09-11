package io.box.doitua.com.boxesmanager.adapters;

import io.box.doitua.com.boxesmanager.db.user.Box;
import io.box.doitua.com.boxesmanager.db.user.User;

public class ResponseAdapter {

    public static User castToDBUser(io.box.doitua.com.boxesmanager.api.models.User user) {
        return new User(user.getName(), user.getEmail());
    }

    public static Box castToDBBox(io.box.doitua.com.boxesmanager.api.models.Box box, int id) {
        return new Box(box.getHeight(), box.getWidth(), box.getLength(), box.getColorName(), box.isNamed(), box.getSize(), id);
    }

    public static io.box.doitua.com.boxesmanager.api.models.User castToServerUser(User user){
        io.box.doitua.com.boxesmanager.api.models.User result = new io.box.doitua.com.boxesmanager.api.models.User();
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setId(user.getId());
        return result;
    }

    public static io.box.doitua.com.boxesmanager.api.models.Box castToServerBox(Box box){
        io.box.doitua.com.boxesmanager.api.models.Box b =  new io.box.doitua.com.boxesmanager.api.models.Box(box.getSize());
        b.setColor(box.getColor());
        b.setNamed(box.isNamed());
        return b;
    }
}
