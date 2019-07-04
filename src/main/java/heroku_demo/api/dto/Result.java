package heroku_demo.api.dto;

public enum Result {
    LOOSE {
        @Override
        public Integer getScore() {
            return 0;
        }
    },
    VICTORY {
        @Override
        public Integer getScore() {
            return 2;
        }
    },
    HALF_VICTORY {
        @Override
        public Integer getScore() {
            return 1;
        }
    };

    public abstract Integer getScore();
}
