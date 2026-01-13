create table users(
                      id BIGSERIAL PRIMARY KEY,
                      email varchar(255) UNIQUE NOT NULL,
                      password varchar(255) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

create table categories(
                           id BIGSERIAL PRIMARY KEY,
                           user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE ,
                           name varchar(100)  NOT NULL,
                           type VARCHAR NOT NULL CHECK(type in('INCOME','EXPENSE')),
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           UNIQUE (user_id,name)
)

create table transactions(
                             id BIGSERIAL PRIMARY KEY,
                             user_id BIGINT  NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                             category_id BIGINT NOT NULL REFERENCES categories(id) ON DELETE RESTRICT,
                             amount DECIMAL(10,2) NOT NULL,
                             description TEXT,
                             date DATE NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

create table budgets(
                        id BIGSERIAL PRIMARY KEY,
                        user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                        category_id BIGINT NOT NULL REFERENCES categories(id) ON DELETE CASCADE,
                        limit_amount DECIMAL(10,2) NOT NULL,
                        period varchar(20) NOT NULL CHECK (period IN('MONTHLY','YEARLY')),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        UNIQUE(user_id,category_id,period)
)

CREATE INDEX idx_transactions_user_date ON transactions (user_id, date DESC);
CREATE INDEX idx_categories_user ON categories(user_id);
CREATE INDEX idx_budgets_user ON budgets(user_id);
CREATE INDEX idx_users_email ON users(email);