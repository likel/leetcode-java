package DesignIn_MemoryFileSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Design an in-memory file system to simulate the following functions:
 *
 * ls: Given a path in string format. If it is a file path, return a list that only contains this
 * file's name. If it is a directory path, return the list of file and directory names in this
 * directory. Your output (file and directory names together) should in lexicographic order.
 *
 * mkdir: Given a directory path that does not exist, you should make a new directory according to
 * the path. If the middle directories in the path don't exist either, you should create them as
 * well. This function has void return type.
 *
 * addContentToFile: Given a file path and file content in string format. If the file doesn't exist,
 * you need to create that file containing given content. If the file already exists, you need to
 * append given content to original content. This function has void return type.
 *
 * readContentFromFile: Given a file path, return its content in string format.
 */
public class FileSystem {

    private Map<String, String> file2Content;
    private Map<String, List<String>> folderItems;
    private List<String> folders;

    public FileSystem() {
        file2Content = new HashMap<>();
        folderItems = new HashMap<>();
        folderItems.put("/", new ArrayList<>());
        folders = new ArrayList<>();
        folders.add("/");
    }

    private String getName(String path, String dirName) {
        String name = path.substring(dirName.length());
        if (name.startsWith("/")) {
            name = name.substring(1);
        }
        int r = name.indexOf('/');
        return r >= 0 ? name.substring(0, r) : name;
    }

    public List<String> ls(String path) {
        if (!folders.contains(path)) {
            int l = path.lastIndexOf('/');
            return Collections.singletonList(path.substring(l + 1));
        }
        List<String> items = folderItems.get(path);
        if (items == null) {
            return Collections.emptyList();
        }
        List<String> files = items.stream().map(e -> getName(e, path)).collect(Collectors.toList());
        Collections.sort(files);
        return files;
    }

    public void mkdir(String path) {
        if (path.equals("/")) {
            return;
        }
        if (!folderItems.containsKey(path)) {
            folderItems.put(path, new ArrayList<>());
        }
        if (!folders.contains(path)) {
            folders.add(path);
        }
        String dir = getDir(path);
        List<String> items = folderItems.computeIfAbsent(dir, key -> new ArrayList<>());
        if (!items.contains(path)) {
            items.add(path);
        }
        mkdir(dir);
    }

    public String getDir(String path) {
        int r = path.lastIndexOf('/');
        return r > 0 ? path.substring(0, r) : "/";
    }

    public void addContentToFile(String filePath, String content) {
        String dir = getDir(filePath);
        mkdir(dir);
        List<String> items = folderItems.computeIfAbsent(dir, key -> new ArrayList<>());
        if (!items.contains(filePath)) {
            items.add(filePath);
        }
        String old = file2Content.computeIfAbsent(filePath, k -> "");
        file2Content.put(filePath, old + content);
    }

    public String readContentFromFile(String filePath) {
        return file2Content.get(filePath);
    }

}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
