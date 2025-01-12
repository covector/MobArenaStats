SELECT
  COUNT(1) AS total_sessions,
  SUM(TIMESTAMPDIFF(second, s.start_time, COALESCE(p.death_time, p.leave_time, s.end_time))) AS total_seconds,
  SUM(p.kills) AS total_kills,
  SUM(p.dmg_done) AS total_dmg_done,
  SUM(CASE WHEN p.dmg_taken < 99999999 THEN p.dmg_taken ELSE 0 END) AS total_dmg_taken,
  SUM(p.last_wave) AS total_waves,
  SUM(case when p.conclusion='VICTORY' then 1 else 0 end) AS total_wins
FROM sessions s
JOIN player_sessions p
  ON p.session_id = s.id
WHERE p.player_name = :player_name;
