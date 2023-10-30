package src.api;

public class Projects {
    private static String name;
    private static boolean favourite;

    public Projects(String name, boolean favourite) {
        this.name = name;
        this.favourite = favourite;
    }

    public static Projects.ProjectsBuilder builder() {
        return new Projects.ProjectsBuilder();
    }

    public static class ProjectsBuilder {
        private String name;
        private boolean favourite;

        ProjectsBuilder() {
        }

        public Projects.ProjectsBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Projects.ProjectsBuilder favourite(boolean favourite) {
            this.favourite = favourite;
            return this;
        }

        public Projects build() {
            return new Projects(name, favourite);
        }
    }

    public static String getName() {
        return name;
    }

    public static boolean getFavourite() {
        return favourite;
    }

}
