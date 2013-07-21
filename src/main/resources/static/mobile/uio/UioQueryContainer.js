Ext.define('uio.UioQueryContainer', {
    extend: 'Ext.Container',
    requires: ['uio.UioRecordViewer', 'Ext.dataview.List'],
    xtype: 'uioquerycontainer',
    fullscreen: true,
    layout: 'fit',
    
    constructor: function(config)
    {
    	config = config || {};
    	console.log("const");
    	config.listeners = {
    			initialize: function(container, opts){
    				console.log("init");
    				Ext.Ajax.request({url: config.url,
    					success: function()
    					{
    						Ext.Viewport.removeAll();
    						Ext.Viewport.add({xtype: 'uioquerygrid', disclosureProperty: 'Country', itemTpl: '{Name}', fields: ['Country', 'Name'], url: '/mad/SqlQry/query/language/execute.json'});
    					}
    					});
    				
    			}
    	};
    	this.callParent([config]);
    }
});
