% this function takes the points and edge vertices and plots them.
 
function plot_points_edges(fig_no, points, edge_vertices_no, color_code,marker_size)
 
marker_vertices={'o', 'v', '>', '<', 's', 'd', 'p', '+'};% we give each vertex a different marker so that we can identify the points after plotting.
 
figure(fig_no); % this makes the figure fig_no active
hold on; % this makes sure that any previous plot is not erased.
 
% first plot points
for point_no=1:size(points,2) % check help on size
    plot3(points(1,point_no),points(2,point_no),points(3,point_no),...
    [color_code marker_vertices{point_no}],'MarkerSize',marker_size);
end
% Then plot edges
for edge_no=1:size(edge_vertices_no,1)
    current_points = edge_vertices_no(edge_no,:)
    plot3(points(1, current_points), points(2, current_points), points(3, current_points),...
    color_code);
end
 
% label the axes
xlabel('x');ylabel('y');zlabel('z');
axis equal;
 
%set an easy to see 3D view
view(3);

end % ends the function
