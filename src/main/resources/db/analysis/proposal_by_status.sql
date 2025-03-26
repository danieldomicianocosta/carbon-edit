-- SQL query to retrieve proposals by status
-- This query is used by the ProposalAnalysisRepository to fetch proposals by status

-- Basic query to get proposals by status
SELECT 
    p.pps_id,
    p.proposal_number,
    p.create_date,
    p.validity_date,
    p.finished_date,
    p.status_cla_id,
    p.customer_name,
    c.cla_description AS status_description
FROM 
    proposal p
JOIN 
    classifier c ON p.status_cla_id = c.cla_id
WHERE 
    p.status_cla_id = :statusId;

-- Query with related data (proposal details and vehicles)
SELECT 
    p.pps_id,
    p.proposal_number,
    p.create_date,
    p.validity_date,
    p.finished_date,
    p.status_cla_id,
    p.customer_name,
    c.cla_description AS status_description,
    pd.purchase_order_service,
    pd.partner_id,
    pd.user_id,
    pdv.model_id
FROM 
    proposal p
JOIN 
    classifier c ON p.status_cla_id = c.cla_id
LEFT JOIN 
    proposal_detail pd ON pd.proposal_id = p.pps_id
LEFT JOIN 
    proposal_detail_vehicle pdv ON pdv.proposal_detail_id = pd.pdt_id
WHERE 
    p.status_cla_id = :statusId;

-- Query with pagination
SELECT 
    p.pps_id,
    p.proposal_number,
    p.create_date,
    p.validity_date,
    p.finished_date,
    p.status_cla_id,
    p.customer_name,
    c.cla_description AS status_description
FROM 
    proposal p
JOIN 
    classifier c ON p.status_cla_id = c.cla_id
WHERE 
    p.status_cla_id = :statusId
ORDER BY 
    p.create_date DESC
LIMIT :pageSize OFFSET :offset;

-- Count query for pagination
SELECT 
    COUNT(p.pps_id)
FROM 
    proposal p
WHERE 
    p.status_cla_id = :statusId;

-- Count proposals grouped by status
SELECT 
    p.status_cla_id,
    COUNT(p.pps_id) as count,
    c.cla_description AS status_description
FROM 
    proposal p
JOIN 
    classifier c ON p.status_cla_id = c.cla_id
GROUP BY 
    p.status_cla_id, c.cla_description
ORDER BY 
    p.status_cla_id;
