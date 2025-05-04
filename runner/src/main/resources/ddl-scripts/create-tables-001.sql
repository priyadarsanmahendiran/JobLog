CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    email_id VARCHAR(100) UNIQUE NOT NULL,
    created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE work_logs (
    worklog_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    log_date DATE NOT NULL,
    tasks_done TEXT,
    tasks_planned TEXT,
    blockers TEXT,
    created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_updated_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE standup_summary (
    id SERIAL PRIMARY KEY,
    user_id UUID NOT NULL,
    summary_date DATE NOT NULL,
    summary TEXT NOT NULL,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uniq_user_date UNIQUE (user_id, summary_date)
);

-- Composite index to speed up your most common query pattern
CREATE INDEX idx_user_logdate ON work_logs(user_id, log_date);