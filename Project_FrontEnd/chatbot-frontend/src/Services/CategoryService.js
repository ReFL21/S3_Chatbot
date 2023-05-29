import httpCommons from "../http-commons";


export const getCategories = () => {
    return httpCommons.get("/nodes");
}

export const getCategoryById = (id) => {
    return httpCommons.get(`/nodes?parentId=${id}`);
}


export const addNode = (json) => {
    return httpCommons.post("/nodes", json);
}

export const getNodeByTopic = (topic) => {
    return httpCommons.get(`/nodes/filter?topic=${topic}`);
}
export const getNodes = () => {
    return httpCommons.get('/nodes/filter');
}
export const getNodeById = (id) => {
    return httpCommons.get(`/nodes/find?id=${id}`);
}


//Needed to get the welcome message from the backend 
export const getNodeByKeywordAndLang = async (keyword, language) => {
    return await httpCommons.get(`/nodes/${keyword}?Lang=${language}`);
}

export const updateRating = (nodeId, feedback) => {
    return httpCommons.put(`/ratings/${nodeId}`, {
        id: nodeId,
        wasItUseful: feedback
    })
}

