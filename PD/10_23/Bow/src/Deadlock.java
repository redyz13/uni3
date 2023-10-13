public class Deadlock {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void bow(Friend bower) {
            synchronized (this) {
                System.out.format("%s: %s has bowed to me!%n", name,
                        bower.getName());
            }
            bower.bowBack(this);
        }

        public void bowBack(Friend bower) {
            synchronized (this) {
                System.out.format("%s: %s has bowed back to me!%n", name,
                        bower.getName());
            }
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");

        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}
