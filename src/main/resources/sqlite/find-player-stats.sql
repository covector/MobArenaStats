SELECT
  COUNT(1) AS total_sessions,
  SUM((COALESCE(p.death_time, p.leave_time, s.end_time) / 1000) - (start_time / 1000)) AS total_seconds,
  SUM(p.kills) AS total_kills,
  SUM(p.last_wave) AS total_waves,
  SUM(case when p.conclusion='VICTORY' then 1 else 0 end) AS total_wins
FROM sessions s
JOIN player_sessions p
  ON p.session_id = s.id
WHERE p.player_name = :player_name;
