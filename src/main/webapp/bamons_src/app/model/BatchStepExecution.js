Ext.define('SpringBatch.model.BatchStepExecution', {
    extend: 'Ext.data.Model',
    alias: 'model.BatchStepExecution',
    fields: [
	    {name: 'jobExecutionId', type: 'int'},
	    {name: 'jobName', type: 'string'},
	    {name: 'jobInstanceId', type: 'int'},
        {name: 'stepExecutionId', type: 'int'},
        {name: 'stepName', type: 'string'},
	    {name: 'startTime', type: 'string'},
	    {name: 'endTime', type: 'string'},
        {name: 'status', type: 'string'},
        {name: 'exitCode', type: 'string'},
        {name: 'exitMessage', type: 'string'}
    ]
});