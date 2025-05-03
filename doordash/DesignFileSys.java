package doordash;

import java.util.HashMap;
import java.util.Map;

/*
 * https://link.1point3acres.com/?url=https://www.lintcode.com/problem/3677/
 */
class FileSystem {
    private Map<String, Integer> pathMap;

    public FileSystem() {
        pathMap = new HashMap<>();
        pathMap.put("", -1); // root placeholder
    }

    public boolean createPath(String path, int value) {
        if (pathMap.containsKey(path))
            return false; // already exists

        int lastSlash = path.lastIndexOf('/');
        String parent = path.substring(0, lastSlash);
        if (!pathMap.containsKey(parent))
            return false; // parent must exist

        pathMap.put(path, value);
        return true;
    }

    public int get(String path) {
        return pathMap.getOrDefault(path, -1);
    }
}
